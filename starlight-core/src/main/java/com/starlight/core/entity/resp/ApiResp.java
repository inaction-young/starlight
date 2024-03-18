package com.starlight.core.entity.resp;


import com.starlight.core.constants.SystemConstants;
import com.starlight.core.enums.ApiCode;
import com.starlight.core.enums.DateFormat;
import com.starlight.core.utils.DateUtils;
import lombok.Data;
import lombok.Getter;


/**
 * API统一的响应类
 * @ClassName: ApiCode
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/6 下午3:12
 * @Version V1.0.0
 */
@Getter
public class ApiResp<T> extends BaseResp {
	/** 系统响应码， 0成功，-1失败 */
	private int rspCode;

	/** 系统响应头 */
	private Header header;

	/** 业务数据对象 */
	private T body;

	public ApiResp(T body) {
        this.rspCode = ApiCode.REP_SUCCESS.getCode();
		this.init(ApiCode.API_SUCCESS, body);
	}
	public ApiResp(ApiCode apiCode) {
        this.rspCode = ApiCode.REP_FAIL.getCode();
		this.init(apiCode, null);
	}

	private void init(ApiCode apiCode, T body){
		this.body = body;
		header = new Header(SystemConstants.SYS_VERSION, DateUtils.now(DateFormat.MillisecondTime));
        setApiCode(apiCode);
	}

	public void setApiCode(ApiCode apiCode) {
		header.setCode(apiCode.getCode());
		header.setMsg(apiCode.getMsg());
	}
    @Data
	public class Header {
		/**
		 * 系统版本
		 */
		private String sysVersion;
		/**
		 * 响应时间
		 */
		private String rspTime;
		/**
		 * 业务错误码， 0 成功，其他为错误码。
		 */
		private int code;
		/**
		 * 业务错误消息
		 */
		private String msg;

		public Header(String sysVersion, String rspTime) {
			this.sysVersion = sysVersion;
			this.rspTime = rspTime;
		}
	}

	public static ApiResp fail(ApiCode apiCode){
		return new ApiResp(apiCode);
	}

	public static <T> ApiResp<T> success(T data){
		return new ApiResp(data);
	}

}
