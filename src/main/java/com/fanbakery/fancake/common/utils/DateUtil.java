package com.fanbakery.fancake.common.utils;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    static Calendar cal = Calendar.getInstance();

    public static Date setDate(String yy, String mm, String dd){
        return calDate(Integer.parseInt(yy), Integer.parseInt(mm), Integer.parseInt(dd));
    }

    public static Date calDate(int yy, int mm, int dd){
        cal.add(Calendar.YEAR, yy);     //년 더하기
        cal.add(Calendar.MONTH, mm);    //월 더하기
        cal.add(Calendar.DATE, dd);     //일 더하기
        return cal.getTime();
    }

    public static Date addDate(int yy, int mm, int dd){
        Calendar date = Calendar.getInstance();
        date.add(Calendar.YEAR, yy);     //년 더하기
        date.add(Calendar.MONTH, mm);    //월 더하기
        date.add(Calendar.DATE, dd);     //일 더하기
        return date.getTime();
    }

    public static Date calDate(Date date, int yy, int mm, int dd){
        cal.setTime(date);
        cal.add(Calendar.YEAR, yy);     //년 더하기
        cal.add(Calendar.MONTH, mm);    //월 더하기
        cal.add(Calendar.DATE, dd);     //일 더하기
        return cal.getTime();
    }

    public static Date setDate(String yy, String mm, String dd, String h, String m, String s){
        cal.setTime(setDate(yy, mm, dd));
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(h));       //시간 더하기
        cal.set(Calendar.MINUTE, Integer.parseInt(m));            //분 더하기
        cal.set(Calendar.SECOND, Integer.parseInt(s));            //초 더하기
        return cal.getTime();
    }

    public static Date calDate(Date date, int yy, int mm, int dd, int h, int m, int s){
        cal.setTime(calDate(date, yy, mm, dd));
        cal.add(Calendar.HOUR, h);      //시간 더하기
        cal.add(Calendar.MINUTE, m);    //분 더하기
        cal.add(Calendar.SECOND, s);    //초 더하기
        return cal.getTime();
    }

    public static String format(Date date, String format){
        SimpleDateFormat transFormat = new SimpleDateFormat(format);
        return transFormat.format(date);
    }

    public static String getMonth(){
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH)+1;
        String _month = (month < 10 ? "0": "") +month;
        return _month;
    }


    /**
     * FUNCTION :: 현재시간 추출
     * @param type (F : 년월일시분초, D : 년월일, T : 시분초)
     * @return
     */
    public String getNowTime(String type){
        String timeResult = "";
        SimpleDateFormat sdf = null;
        Calendar cal = Calendar.getInstance();
        switch (type){
            case "F" : sdf = new SimpleDateFormat("yyyyMMddHHmmss"); break;
            case "D" : sdf = new SimpleDateFormat("yyyyMMdd"); break;
            case "T" : sdf = new SimpleDateFormat("HHmmss"); break;
            default : sdf = new SimpleDateFormat("yyyyMMddHHmmss"); break;
        }
        return timeResult = sdf.format(cal.getTime());
    }

    /**
     * FUNCTION :: 문자 -> 날짜로 변경
     * @param date
     * @param format
     * @return
     */
    public static Date stringToDate(String date, String format){
        SimpleDateFormat f = new SimpleDateFormat(format);
        Date d = new Date();
        try{
            d = f.parse(date);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return d;
        }
    }

    /**
     * FUNCTION :: 날짜 -> 문자로 변경
     * @param date
     * @param format
     * @return
     */
    public static String dateToString(Date date, String format){
        SimpleDateFormat fm = new SimpleDateFormat(format);
        return fm.format(date);
    }


    public static String getCurrentYear(){
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy", Locale.KOREA );
        Date currentTime = new Date();
        String mTime = mSimpleDateFormat.format ( currentTime );
        return mTime;
    }


    /**
     * FUNCTION :: 날짜 차이 "D-x xx:xx:xx" 로 변환해서 전달
     * @param from
     * @param to
     * @return
     */
    public static String formatDurationBetween(LocalDateTime from, LocalDateTime to) {
        Duration diff = Duration.between(from, to);
        if (diff.isZero()) {
            return String.format("D-0 00:00:00");
        } else {
            long days = diff.toDays();
            if (days != 0) {
                diff = diff.minusDays(days);
            }
            long hours = diff.toHours();
            if (hours != 0) {
                diff = diff.minusHours(hours);
            }
            long minutes = diff.toMinutes();
            if (minutes != 0) {
                diff = diff.minusMinutes(minutes);
            }
            long seconds = diff.getSeconds();

            return String.format("D-%d %02d:%02d:%02d", days, hours, minutes, seconds);
        }
    }

    /**
     * FUNCTION :: from,to 간의 시간차 (hour)
     * @param from
     * @param to
     * @return
     */
    public static Long getHourDurationBetween(LocalDateTime from, LocalDateTime to) {
        Duration diff = Duration.between(from, to);
        if (diff.isZero()) {
            return 0L;
        }

        return diff.toHours();
    }

}
