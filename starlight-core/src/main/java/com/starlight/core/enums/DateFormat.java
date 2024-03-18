package com.starlight.core.enums;

/**
 *
 * @ClassName: DateFormat
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/9 下午3:11
 * @Version V1.0.0
 */
/** 时间格式化 **/
public enum DateFormat implements IEnum {
    ShortNumDate("yyMMdd"),

    NumDate("yyyyMMdd"),

    StrikeDate("yyyy-MM-dd"),

    NumDateTime("yyyyMMddHHmmss"),

    TwoYearNumDateTime("yyMMddHHmmss"),

    StrikeDateTime("yyyy-MM-dd HH:mm:ss"),

    DoubleDateTime("yyyyMMddHHmmss.SSS"),

    MillisecondTime("yyyy-MM-dd HH:mm:ss SSS"),

    NumTime("HHmmss"),

    ColonTime("HH:mm:ss");

    DateFormat(String value) {
	    changeNameTo(this, value);
    }
}
