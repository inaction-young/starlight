package com.starlight.cached;

import com.starlight.core.constants.CachedKeys;
import com.starlight.enums.user.UserElementType;
import com.starlight.model.UserElement;
import com.starlight.service.UserElementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @ClassName: UserElementCached
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/14 下午3:54
 * @Version V1.0
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class UserElementCached {

    private final UserElementService userElementService;

    @Cacheable(value = CachedKeys.USER_ELEMENT, key = "#p0 + '-' + #p1.name")
    public UserElement getByElement(String element, UserElementType type) {
        return userElementService.lambdaQuery()
                .eq(UserElement::getElement, element)
                .eq(UserElement::getType, type)
                .one();
    }


}
