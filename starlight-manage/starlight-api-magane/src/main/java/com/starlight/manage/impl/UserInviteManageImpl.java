package com.starlight.manage.impl;

import com.starlight.core.enums.ApiCode;
import com.starlight.core.enums.RedisKeys;
import com.starlight.core.exception.ApiException;
import com.starlight.core.utils.MathUtils;
import com.starlight.core.utils.RedisUtils;
import com.starlight.core.utils.SnowflakeIdUtils;
import com.starlight.core.utils.SpringUtils;
import com.starlight.core.entity.bo.UserToken;
import com.starlight.enums.user.UserInviteStatus;
import com.starlight.manage.UserInviteManage;
import com.starlight.model.UserInvite;
import com.starlight.service.UserInviteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @ClassName: UserInviteManageImpl
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/9 上午11:22
 * @Version V1.0
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class UserInviteManageImpl implements UserInviteManage {

    private final UserInviteService userInviteService;

    @Override
    public String createLink(UserToken userToken) {
        String inviteLinkKey = RedisKeys.INVITE_LINK.formatKey(userToken.getUuid());
        Object linkInRedis = RedisUtils.get(inviteLinkKey);
        //原来就有
        if (Objects.nonNull(linkInRedis)) {
            return linkInRedis.toString();
        }

        //创建key和链接
        String unique = SnowflakeIdUtils.unique();
        String link = new StringBuilder(SpringUtils.confValue("starlight.domain"))
                .append(SpringUtils.confValue("starlight.invite.uri"))
                .append("/")
                .append(unique)
                .toString();
        RedisUtils.set(inviteLinkKey, link, RedisKeys.INVITE_LINK.getExpire());

        //key绑定用户
        String redisKey = RedisKeys.INVITE_KEY.formatKey(unique);
        RedisUtils.set(redisKey, userToken, RedisKeys.INVITE_KEY.getExpire());
        return link;
    }

    @Override
    public String link(String key) {
        String redisKey = RedisKeys.INVITE_KEY.formatKey(key);
        if (Objects.isNull(RedisUtils.get(redisKey))) {
            throw new ApiException(ApiCode.API_INVITE_EXPIRE);
        }

        StringBuilder url = new StringBuilder(SpringUtils.confValue("user.invite.login"));
        if (url.indexOf("?") > -1) {
            return url.append("&inviteKey=").append(key).toString();
        }
        return url.append("?inviteKey=").append(key).toString();
    }

    @Override
    public long countByUuid(long uuid) {
        Integer count = userInviteService.lambdaQuery()
                .eq(UserInvite::getUuid, uuid)
                .in(UserInvite::getStatus, UserInviteStatus.ACTIVATE, UserInviteStatus.PRIZE)
                .count();
        return MathUtils.nvl(count);
    }

    @Transactional(rollbackFor = ApiException.class)
    public void inviteActivate(String key, long inviteUuid) {
        try {
            String redisKey = RedisKeys.INVITE_KEY.formatKey(key);
            Object o = RedisUtils.get(redisKey);
            if (Objects.isNull(o)) {
                throw new ApiException(ApiCode.API_INVITE_EXPIRE);
            }
            UserToken userToken = (UserToken) o;
            UserInvite userInvite = new UserInvite();
            userInvite.setUuid(userToken.getUuid());
            userInvite.setInvitedUuid(inviteUuid);
            userInvite.setStatus(UserInviteStatus.PRIZE);
            if (!userInviteService.save(userInvite)) {
                throw new ApiException("邀请注册失败");
            }
            //Todo: 发放钻石暂未实现




        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiException(e);
        }
    }
}
