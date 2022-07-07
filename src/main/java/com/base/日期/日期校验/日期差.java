package com.base.日期.日期校验;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class 日期差 {

    // 天数差 = secondDate - firstDate [regex 为日期分隔符]
    public static long calculateTimeDifferenceDayByCalendar(String startDateStr, String endDateStr, String regex) {
        String startDateStrs[] = startDateStr.split(regex);
        String endDateStrs[] = endDateStr.split(regex);
        LocalDate startDate = LocalDate.of(Integer.valueOf(startDateStrs[0]), Integer.valueOf(startDateStrs[1]), Integer.valueOf(startDateStrs[2]));
        LocalDate endDate = LocalDate.of(Integer.valueOf(endDateStrs[0]), Integer.valueOf(endDateStrs[1]), Integer.valueOf(endDateStrs[2]));
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    public static void main(String[] args) {
        System.out.println(calculateTimeDifferenceDayByCalendar("2018-10-13","2021-11-30","-"));
    }

}
