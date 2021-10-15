package com.enercomn.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Oliver
 * @version 1.0
 * @description 时间工具类
 * @date 2019/6/4 17:02
 */
@SuppressWarnings({"all"})
public class DateTimeUtils {

    public final static String YYYYMMDD = "yyyyMMdd";

    public final static String YYYY_MM_DD = "yyyy-MM-dd";

    public final static String YYYY_MM_DD_2 = "yyyy/MM/dd";

    public final static String YYYY_MM = "yyyy-MM";

    public final static String YYYY = "yyyy";

    public final static String MM_DD = "MM-dd";

    public final static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH-mm-ss";

    public final static String YYYY_MM_DD_HH_MM_SS_POINT = "yyyy-MM-dd HH:mm:ss";

    public final static String[] HOURARR = {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};

    public final static String[] MONTHARR = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};

    /**
     * 添加的开始时间
     */
    public final static String STAR_TIME_HMS = " 00:00:00";
    /**
     * 添加的结束时间
     */
    public final static String END_TIME_HMS = " 23:59:59";

    private static Calendar dateStartCalendar(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c;
    }

    private static Calendar dateEndCalendar(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 0);
        return c;
    }

    public static String dateStartFormat(String date, String format) {
        if (StringUtils.isEmpty(format)) {
            format = YYYY_MM_DD;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar c = dateStartCalendar(paseDate(date, format));
        return sdf.format(c.getTime());
    }

    public static String dateStartFormat(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
        Calendar c = dateStartCalendar(date);
        return sdf.format(c.getTime());
    }

    public static String dateStartFormat(Date date, String format) {
        if (StringUtils.isEmpty(format)) {
            SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
            Calendar c = dateStartCalendar(date);
            return sdf.format(c.getTime());
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Calendar c = dateStartCalendar(date);
            return sdf.format(c.getTime());
        }

    }

    public static String dateEndFormat(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
        Calendar c = dateEndCalendar(date);
        return sdf.format(c.getTime());
    }

    public static String dateEndFormat(String date, String format) {
        if (StringUtils.isEmpty(format)) {
            format = YYYY_MM_DD;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar c = dateEndCalendar(paseDate(date, format));
        return sdf.format(c.getTime());
    }

    public static String dateEndFormat(Date date, String format) {
        if (StringUtils.isEmpty(format)) {
            SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
            Calendar c = dateEndCalendar(date);
            return sdf.format(c.getTime());
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Calendar c = dateEndCalendar(date);
            return sdf.format(c.getTime());
        }
    }

    public static Date getEndFormatDate(Date date, String format) {
        if (StringUtils.isEmpty(format)) {
            SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
            Calendar c = dateEndCalendar(date);
            return c.getTime();
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Calendar c = dateEndCalendar(date);
            return c.getTime();
        }
    }

    public static Date dateStart(Date date) {
        Calendar c = dateStartCalendar(date);
        return c.getTime();
    }

    public static Date dateEnd(Date date) {
        Calendar c = dateEndCalendar(date);
        return c.getTime();
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param date
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param date
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(String date) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(paseDate(date, null));
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public static List<String> getWeekDay(Date date) {
        List<String> dayOfWeek = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        String imptimeBegin = sdf.format(cal.getTime());
        dayOfWeek.add(imptimeBegin);
        for (int i = 0; i < 6; i++) {
            cal.add(Calendar.DATE, 1);
            String imptime = sdf.format(cal.getTime());
            dayOfWeek.add(imptime);
        }
        return dayOfWeek;
    }

    public static List<String> getMonthDay(String date) {
        List<String> dayOfMonth = new ArrayList<>();
        Calendar cd = Calendar.getInstance();
        cd.setTime(paseDate(date));
        int year = cd.get(Calendar.YEAR);
        int month = cd.get(Calendar.MONTH);
        int day = 0;

        int[] monDays = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (((year) % 4 == 0 && (year) % 100 != 0) || (year) % 400 == 0) {
            day = monDays[month]++;
        } else {
            day = monDays[month];
        }

        for (int i = 1; i <= day; i++) {
            if (i < 10) {
                if (month + 1 < 10) {
                    dayOfMonth.add(year + "-0" + (month + 1) + "-0" + i);
                } else {
                    dayOfMonth.add(year + "-" + (month + 1) + "-0" + i);
                }
            } else {
                if (month + 1 < 10) {
                    dayOfMonth.add(year + "-0" + (month + 1) + "-" + i);
                } else {
                    dayOfMonth.add(year + "-" + (month + 1) + "-" + i);
                }
            }
        }

        return dayOfMonth;
    }

    public static List<String> getDaysOfMonth(String date) {
        List<String> dayOfMonth = new ArrayList<>();
        Calendar cd = Calendar.getInstance();
        cd.setTime(paseDate(date));
        int year = cd.get(Calendar.YEAR);
        int month = cd.get(Calendar.MONTH);
        int day = 0;

        int[] monDays = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (((year) % 4 == 0 && (year) % 100 != 0) || (year) % 400 == 0) {
            day = monDays[month]++;
        } else {
            day = monDays[month];
        }

        for (int i = 1; i <= day; i++) {
            if (i < 10) {
                dayOfMonth.add("0" + i);
            } else {
                dayOfMonth.add("" + i);
            }
        }

        return dayOfMonth;
    }

    public static List<String> getDaysBetween(Date date, Date dateEnd) {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
        int days = (int) ((dateEnd.getTime() - date.getTime()) / (1000 * 3600 * 24));
        List<String> dayOfBetween = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        dayOfBetween.add(sdf.format(date));
        for (int i = 0; i < days; i++) {
            cal.add(Calendar.DATE, 1);
            dayOfBetween.add(sdf.format(cal.getTime()));
        }
        return dayOfBetween;
    }

    public static List<String> getFormartDaysBetween(Date date, Date dateEnd, String formart) {
        List<String> dayOfBetween = new ArrayList<>();
        if (StringUtils.isNotEmpty(formart)) {
            SimpleDateFormat sdf = new SimpleDateFormat(formart);
            int days = (int) ((dateEnd.getTime() - date.getTime()) / (1000 * 3600 * 24));
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            dayOfBetween.add(sdf.format(date));
            for (int i = 0; i < days; i++) {
                cal.add(Calendar.DATE, 1);
                dayOfBetween.add(sdf.format(cal.getTime()));
            }
        }
        return dayOfBetween;
    }

    public static List<String> getDaysBetween(String date, String dateEnd) {
        Date tempDate = paseDate(date);
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
        int days = (int) ((paseDate(dateEnd).getTime() - tempDate.getTime()) / (1000 * 3600 * 24));
        List<String> dayOfBetween = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(tempDate);
        dayOfBetween.add(sdf.format(tempDate));
        for (int i = 0; i < days; i++) {
            cal.add(Calendar.DATE, 1);
            dayOfBetween.add(sdf.format(cal.getTime()));
        }
        return dayOfBetween;
    }

    /**
     * 获取指定日期所在月的第一天
     */
    public static Date getFirstOfMonth(Date date) {
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, 0);
        //设置为1号,当前日期既为本月第一天
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取指定日期所在月的第一天
     */
    public static String getFirstOfMonth(String date) {
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.setTime(paseDate(date));
        c.add(Calendar.MONTH, 0);
        //设置为1号,当前日期既为本月第一天
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return dateStartFormat(c.getTime(), YYYY_MM_DD_HH_MM_SS_POINT);
    }

    /**
     * 获取指定日期去年所在月的第一天
     */
    public static Date getFirstOfLastYearMonth(Date date) {
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.YEAR, c.get(Calendar.YEAR) - 1);
        c.add(Calendar.MONTH, 0);
        //设置为1号,当前日期既为本月第一天
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取指定日期所在月的最后一天
     */
    public static Date getLastOfMonth(Date date) {
        //获取当前月最后一天
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }


    /**
     * 获取指定日期所在月的最后一天
     */
    public static String getLastOfMonth(String date) {
        //获取当前月最后一天
        Calendar c = Calendar.getInstance();
        c.setTime(paseDate(date));
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 0);
        return dateEndFormat(c.getTime(), YYYY_MM_DD_HH_MM_SS_POINT);
    }

    /**
     * 获取指定日期所在月的最后一天
     */
    public static Date getLastOfLastYearMonth(Date date) {
        //获取当前月最后一天
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.YEAR, c.get(Calendar.YEAR) - 1);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 每周的第一天
     */
    public static Date getFirstOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int d = 0;
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            d = -6;
        } else {
            d = 2 - cal.get(Calendar.DAY_OF_WEEK);
        }
        cal.add(Calendar.DAY_OF_WEEK, d);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        // 所在周开始日期
        return cal.getTime();

    }

    /**
     * 每周的第一天
     */
    public static String getWeekStart(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int d = 0;
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            d = -6;
        } else {
            d = 2 - cal.get(Calendar.DAY_OF_WEEK);
        }
        cal.add(Calendar.DAY_OF_WEEK, d);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        // 所在周开始日期
        return dateStartFormat(cal.getTime(), YYYY_MM_DD_HH_MM_SS_POINT);

    }

    /**
     * 上周的第一天
     */
    public static String getLastWeekStart(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -6);
        int d = 0;
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            d = -6;
        } else {
            d = 2 - cal.get(Calendar.DAY_OF_WEEK);
        }
        cal.add(Calendar.DAY_OF_WEEK, d);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        // 所在周开始日期
        return dateStartFormat(cal.getTime(), YYYY_MM_DD_HH_MM_SS_POINT);
    }

    /**
     * 每周的最后一天
     */
    public static Date getLastOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int d = 0;
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            d = -6;
        } else {
            d = 2 - cal.get(Calendar.DAY_OF_WEEK);
        }
        cal.add(Calendar.DAY_OF_WEEK, d);
        cal.add(Calendar.DAY_OF_WEEK, 6);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 0);
        // 所在周结束日期
        return cal.getTime();

    }

    /**
     * 每周的最后一天
     */
    public static String getWeekEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int d = 0;
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            d = -6;
        } else {
            d = 2 - cal.get(Calendar.DAY_OF_WEEK);
        }
        cal.add(Calendar.DAY_OF_WEEK, d);
        cal.add(Calendar.DAY_OF_WEEK, 6);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 0);
        // 所在周结束日期
        return dateEndFormat(cal.getTime(), YYYY_MM_DD_HH_MM_SS_POINT);

    }

    /**
     * 上周的最后一天
     */
    public static String getLastWeekEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -6);
        int d = 0;
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            d = -6;
        } else {
            d = 2 - cal.get(Calendar.DAY_OF_WEEK);
        }
        cal.add(Calendar.DAY_OF_WEEK, d);
        cal.add(Calendar.DAY_OF_WEEK, 6);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 0);
        // 所在周结束日期
        return dateEndFormat(cal.getTime(), YYYY_MM_DD_HH_MM_SS_POINT);

    }

    /**
     * 获取某年第一天日期
     */
    public static Date getFirstOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取某年第一天日期
     */
    public static String getFirstOfYear(String date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(paseDate(date));
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return dateStartFormat(calendar.getTime(), YYYY_MM_DD_HH_MM_SS_POINT);
    }

    /**
     * 获取去年第一天日期
     */
    public static String getLastYearStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, -1);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return dateStartFormat(calendar.getTime(), YYYY_MM_DD_HH_MM_SS_POINT);
    }

    /**
     * 获取某年第一天日期
     */
    public static String getFirstOfYear(Date date, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return dateFormat(calendar.getTime(), format);
    }

    /**
     * 获取某年最后一天日期
     */
    public static Date getLastOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取某年最后一天日期
     */
    public static String getLastOfYear(String date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(paseDate(date));
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return dateEndFormat(calendar.getTime(), YYYY_MM_DD_HH_MM_SS_POINT);
    }

    /**
     * 获取去年最后一天日期
     */
    public static String getLastYearEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.YEAR, -1);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return dateEndFormat(calendar.getTime(), YYYY_MM_DD_HH_MM_SS_POINT);
    }

    /**
     * 获取某年最后一天日期
     */
    public static String getLastOfYear(Date date, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return dateFormat(calendar.getTime(), format);
    }

    /**
     * 获取指定时间前一天的日期
     */
    public static Date getStartYesterday(Date date, String format) {
        Calendar calendar = dateStartCalendar(date);
        calendar.set(Calendar.HOUR_OF_DAY, -24);
        if (StringUtils.isEmpty(format)) {
            SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
            return paseDate(sdf.format(calendar.getTime()), YYYY_MM_DD);
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return paseDate(sdf.format(calendar.getTime()), format);
        }
    }

    /**
     * 获取指定时间前一天的日期
     */
    public static Date getStartTommow(Date date, String format) {
        Calendar calendar = dateStartCalendar(date);
        calendar.set(Calendar.HOUR_OF_DAY, 24);
        if (StringUtils.isEmpty(format)) {
            SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
            return paseDate(sdf.format(calendar.getTime()), YYYY_MM_DD);
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return paseDate(sdf.format(calendar.getTime()), format);
        }
    }

    /**
     * 获取指定时间前一天的日期
     */
    public static String getYesterdayStart(Date date, String format) {
        Calendar calendar = dateStartCalendar(date);
        calendar.set(Calendar.HOUR_OF_DAY, -24);
        SimpleDateFormat sdf = null;
        if (StringUtils.isEmpty(format)) {
            sdf = new SimpleDateFormat(YYYY_MM_DD);
        } else {
            sdf = new SimpleDateFormat(format);
        }
        return sdf.format(calendar.getTime());
    }


    /**
     * 获取指定时间前一天的日期
     */
    public static String getTommowStart(Date date, String format, int num) {
        Calendar calendar = dateStartCalendar(date);
        calendar.set(Calendar.HOUR_OF_DAY, num);
        SimpleDateFormat sdf = null;
        if (StringUtils.isEmpty(format)) {
            sdf = new SimpleDateFormat(YYYY_MM_DD);
        } else {
            sdf = new SimpleDateFormat(format);
        }
        return sdf.format(calendar.getTime());
    }

    /**
     * 重写方法 获取指定时间前一天的日期
     */
    public static Date getStartYesterday(Date date, String format, int num) {
        Calendar calendar = dateStartCalendar(date);
        calendar.set(Calendar.HOUR_OF_DAY, -num);
        if (StringUtils.isEmpty(format)) {
            SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
            return paseDate(sdf.format(calendar.getTime()), YYYY_MM_DD);
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return paseDate(sdf.format(calendar.getTime()), format);
        }
    }

    public static Date getEndYesterday(Date date, String format) {
        Calendar calendar = dateEndCalendar(date);
        calendar.set(Calendar.HOUR_OF_DAY, -1);
        if (StringUtils.isEmpty(format)) {
            SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
            return paseDate(sdf.format(calendar.getTime()), YYYY_MM_DD);
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return paseDate(sdf.format(calendar.getTime()), format);
        }
    }

    public static String getYesterdayEnd(Date date, String format) {
        Calendar calendar = dateEndCalendar(date);
        calendar.set(Calendar.HOUR_OF_DAY, -1);
        SimpleDateFormat sdf = null;
        if (StringUtils.isEmpty(format)) {
            sdf = new SimpleDateFormat(YYYY_MM_DD);
        } else {
            sdf = new SimpleDateFormat(format);
        }
        return sdf.format(calendar.getTime());
    }

    public static Date paseDate(String date, String formart) {
        try {
            if (StringUtils.isEmpty(formart)) {
                //创建SimpleDateFormat对象实例并定义好转换格式
                SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
                return sdf.parse(date);
            } else {
                //创建SimpleDateFormat对象实例并定义好转换格式
                SimpleDateFormat sdf = new SimpleDateFormat(formart);
                return sdf.parse(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("时间转换异常");
        }
    }

    /**
     * 转换时间
     */
    public static Date paseDate(String date) {
        try {
            //创建SimpleDateFormat对象实例并定义好转换格式
            SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("时间转换异常");
        }
    }

    public static String paseDateString(String date, String formart) {
        try {
            if (StringUtils.isEmpty(formart)) {
                //创建SimpleDateFormat对象实例并定义好转换格式
                SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
                return sdf.format(sdf.parse(date));
            } else {
                //创建SimpleDateFormat对象实例并定义好转换格式
                SimpleDateFormat sdf = new SimpleDateFormat(formart);
                return sdf.format(sdf.parse(date));
            }
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("时间转换异常");
        }
    }

    /**
     * 包含今天在内的七天前
     *
     * @param date
     * @param format
     * @return
     */
    public static String getBeforeSevenDayDateString(Date date, String format) {
        if (StringUtils.isEmpty(format)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar c = Calendar.getInstance();

            //过去七天
            c.setTime(new Date());
            c.add(Calendar.DATE, -6);
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
            Date d = c.getTime();
            String day = dateFormat.format(d);
            //System.out.println("过去七天："+day);
            return day;
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            Calendar c = Calendar.getInstance();

            //过去七天
            c.setTime(new Date());
            c.add(Calendar.DATE, -6);
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);

            Date d = c.getTime();
            String day = dateFormat.format(d);
            //System.out.println("过去七天："+day);
            return day;
        }
    }


    /**
     * 包含今天在内的七天前
     *
     * @param date
     * @param format
     * @return
     */
    public static String getBeforeSomeDayDateString(Date date, String format, int days) {
        if (StringUtils.isEmpty(format)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar c = Calendar.getInstance();

            //过去七天
            c.setTime(new Date());
            c.add(Calendar.DATE, -days);
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
            Date d = c.getTime();
            String day = dateFormat.format(d);
            //System.out.println("过去七天："+day);
            return day;
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            Calendar c = Calendar.getInstance();

            //过去七天
            c.setTime(new Date());
            c.add(Calendar.DATE, -days);
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);

            Date d = c.getTime();
            String day = dateFormat.format(d);
            //System.out.println("过去七天："+day);
            return day;
        }
    }


    public static Date getBeforeSomeDayDate(Date date, int days) {

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -days);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Date d = c.getTime();
        //System.out.println("过去七天："+day);
        return d;

    }

    /**
     * 包含今天在内的七天前
     *
     * @param date
     * @param format
     * @return
     */
    public static String getBeforeSomeDayEndDateString(Date date, String format, int days) {
        if (StringUtils.isEmpty(format)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar c = Calendar.getInstance();

            //过去七天
            c.setTime(new Date());
            c.add(Calendar.DATE, -days);
            c.set(Calendar.HOUR_OF_DAY, 23);
            c.set(Calendar.MINUTE, 59);
            c.set(Calendar.SECOND, 59);
            c.set(Calendar.MILLISECOND, 0);
            Date d = c.getTime();
            String day = dateFormat.format(d);
            //System.out.println("过去七天："+day);
            return day;
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            Calendar c = Calendar.getInstance();

            //过去七天
            c.setTime(new Date());
            c.add(Calendar.DATE, -days);
            c.set(Calendar.HOUR_OF_DAY, 23);
            c.set(Calendar.MINUTE, 59);
            c.set(Calendar.SECOND, 59);
            c.set(Calendar.MILLISECOND, 0);

            Date d = c.getTime();
            String day = dateFormat.format(d);
            //System.out.println("过去七天："+day);
            return day;
        }
    }

    /**
     * 包含今天在内的七天前
     *
     * @param date
     * @param format
     * @return
     */
    public static Date getBeforeSevenDay(Date date, String format) {
        if (StringUtils.isEmpty(format)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar c = Calendar.getInstance();

            //过去七天
            c.setTime(date);
            c.add(Calendar.DATE, -7);
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
            Date d = c.getTime();
            return d;
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            Calendar c = Calendar.getInstance();

            //过去七天
            c.setTime(date);
            c.add(Calendar.DATE, -7);
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);

            Date d = c.getTime();
            return d;
        }
    }


    public static String dateFormat(Date date, String format) {
        if (StringUtils.isEmpty(format)) {
            SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return sdf.format(c.getTime());
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return sdf.format(c.getTime());
        }
    }

    /**
     * 获取前一个小时的时间  默认yyyy-MM-dd HH这个格式
     *
     * @return
     */
    public static String getOneHourBeforeNow() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
        return df.format(calendar.getTime());

    }

    /**
     * 获取前一个小时的时间  默认yyyy-MM-dd HH这个格式
     *
     * @return
     */
    public static String getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
        return df.format(calendar.getTime());

    }

    /**
     * 将时间转换为时间戳
     *
     * @param s
     * @return
     * @throws Exception
     */
    public static String dateToStamp(String s) throws Exception {
        String res;//设置时间格式，将该时间格式的时间转换为时间戳
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long time = date.getTime();
        res = String.valueOf(time);
        return res;
    }


    /**
     * 将时间戳转换为时间
     *
     * @param s
     * @return
     * @throws Exception
     */
    public static String stampToTime(String s) throws Exception {
        //秒级补成毫秒级
        if (s.length() == 10) {
            s = s + "000";
        }
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);//将时间戳转换为时间
        Date date = new Date(lt);//将时间调整为yyyy-MM-dd HH:mm:ss时间样式
        res = simpleDateFormat.format(date);
        return res;
    }


    public static String getBeforeHourTime() {
        Calendar calendar = Calendar.getInstance();
        /* HOUR_OF_DAY 指示一天中的小时 */
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(calendar.getTime());
    }

    public static String getSpecifiedDayBefore(String specifiedDay) {
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);

        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayBefore;
    }

    public static String getSpecifiedDayAfter(String specifiedDay) {
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);

        String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayAfter;
    }

    public static String getSpecifiedDayBeforeStart(String specifiedDay, String format) {
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat(format).parse(specifiedDay);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        String dayBefore = new SimpleDateFormat(format).format(c.getTime());
        return dayBefore;
    }

    public static String getSpecifiedDayAfterEnd(String specifiedDay, String format) {
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat(format).parse(specifiedDay);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 59);

        String dayBefore = new SimpleDateFormat(format).format(c.getTime());
        return dayBefore;
    }

    /**
     * 获取某天之后的n天日期
     */
    public static String getDateAfterSomeDay(Date date, int days, String format) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 59);

        String dayBefore = new SimpleDateFormat(format).format(c.getTime());
        return dayBefore;
    }

    /**
     * 获取某天之后的n天日期
     */
    public static String getDateAfterSomeDay(Date date, int days) {
        String format = "yyyy-MM-dd";
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + days);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 59);

        String dayBefore = new SimpleDateFormat(format).format(c.getTime());
        return dayBefore;
    }

    public static String getMonth() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
        return df.format(calendar.getTime());
    }

    public static String getMonth(Date month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(month);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
        return df.format(calendar.getTime());
    }

    public static String getLastYearMonth() {
        Calendar calendar = Calendar.getInstance();
        /* HOUR_OF_DAY 指示一天中的小时 */
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
        return df.format(calendar.getTime());
    }

    public static String getLastYearMonth(Date month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(month);
        /* HOUR_OF_DAY 指示一天中的小时 */
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
        return df.format(calendar.getTime());
    }

    /**
     * java 获取 获取某年某月 所有日期（yyyy-mm-dd格式字符串）
     *
     * @return
     */
    public static List<String> getFullMonth(Date date) {
        String dateString = dateEndFormat(date, YYYY_MM_DD);
        String[] dateArr = dateString.split("-");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        List<String> fullDayList = new ArrayList<>(12);
        // 获得当前日期对象
        Calendar cal = Calendar.getInstance();
        cal.clear();// 清除信息
        cal.set(Calendar.YEAR, Integer.parseInt(dateArr[0]));
        for (int j = 1; j <= 12; j++) {
            // 1月从0开始
            cal.set(Calendar.MONTH, j - 1);
            fullDayList.add(dateFormat.format(cal.getTime()));
        }
        return fullDayList;
    }

    /*
    判读时间差距，两个时间相差多少天，时，分，秒
     */
    public static Long getDay(String firstDate, String secondDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long days = null;
        try {
            Date currentTime = dateFormat.parse(secondDate);//现在系统当前时间
            Date pastTime = dateFormat.parse(firstDate);//过去时间
            long diff = currentTime.getTime() - pastTime.getTime();
            days = diff / (1000 * 60 * 60 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    //获取两个时间之间的日期
    public static int getDatePoor(Date startDate, Date endDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(endDate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        int parseInt = Integer.parseInt(String.valueOf(between_days));
        parseInt = parseInt + 1;
        return parseInt;

    }

    public static String before24hours( Date date,String format) {
        String endTime = DateTimeUtils.dateFormat(date, format);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);
        return new SimpleDateFormat(format).format(c.getTime());
    }

    public static String beforeMonth1day( Date date,String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return new SimpleDateFormat(format).format(calendar.getTime());
    }

    public static String year1Day1Month(Date date,String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int currentYear = calendar.get(Calendar.YEAR);
        calendar.clear();
        calendar.set(Calendar.YEAR, currentYear);
        return new SimpleDateFormat(format).format(calendar.getTime());
    }

}
