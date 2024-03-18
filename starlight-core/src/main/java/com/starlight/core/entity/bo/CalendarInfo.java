package com.starlight.core.entity.bo;

import java.io.Serializable;

/**
 *
 * @ClassName: CalendarInfo
 * @Description:
 * @Author: Tj
 * @Date: 2023/11/6 下午2:17
 * @Version V1.0.0
 */
/** 日历信息 **/
public class CalendarInfo<T> implements Serializable {
    private static final long serialVersionUID = -7000598135789499812L;

    /** 日期天  **/
    public Integer day;

     /** 周几  **/
    public Integer weekDay;

     /** 是否今天  **/
    public Boolean isToday;

     /** 日历信息  **/
    public T info;
}
