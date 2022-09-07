package com.szq.securityanalysis.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author szq
 * @Description TODO
 * @Date 2022/8/23 0:13
 * @Version 1.0.0
 */
public class DateUtils {
    public static String date2String(Date date, String pattern){
        SimpleDateFormat sdf1 = new SimpleDateFormat(pattern);
        String strDate = sdf1.format(date);
        return strDate;
    }
}
