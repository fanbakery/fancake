package com.fanbakery.fancake.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

public class CommonUtil {

    public static final String IS_MOBILE = "MOBILE";
    public static final String IS_PHONE = "PHONE";
    public static final String IS_TABLET = "TABLET";
    public static final String IS_PC = "PC";

    /**
     * FUNCTION : 사용자 접근 IP 추출
     * @return String ip
     */
    public static String getClientIP() {
        HttpServletRequest reqeust = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = reqeust.getHeader("X-FORWARDED-FOR");
        if (ip == null) ip = reqeust.getRemoteAddr();
        return ip;
    }

    /**
     * FUNCTION :: 서버 IP 추출
     * @return
     */
    public static String getServerIp(){
        InetAddress local;
        String ip = "";
        try {
            local = InetAddress.getLocalHost();
            ip = local.getHostAddress();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            return ip;
        }
    }

    /**
     * FUNCTION : 모바일, 태블릿, PC 구분
     * @return
     */
    public static String isDevice(){
        HttpServletRequest reqeust = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String userAgent = reqeust.getHeader("User-Agent").toUpperCase();

        if(userAgent.indexOf(IS_MOBILE) > -1) {
            if(userAgent.indexOf(IS_PHONE) == -1)
                return IS_MOBILE;
            else
                return IS_TABLET;
        } else
            return IS_PC;
    }

    /***
     * FUNCTION : 보여지는 화면의 uri 주소를 반환
     * @return
     */
    public static String applicationUri() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String strUri = request.getRequestURI();
        strUri = strUri.substring(0,strUri.indexOf("/",4));
        return strUri;
    }

    /**
     * FUNCTION : 랜덤 키값 생성
     * @param count
     * @return
     */
    public static String randomAlphaNumeric(int count) {
        String alpha_numeric_string = "abcdefghijklmnopqrstuvwxyABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*_-";
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * alpha_numeric_string.length());
            builder.append(alpha_numeric_string.charAt(character));
        }
        return builder.toString();
    }

    /**
     * FUNCTION : 숫자 랜덤 키값 생성
     * @param count
     * @return
     */
    public static String randomNumeric(int count) {
        String alpha_numeric_string = "0123456789";
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * alpha_numeric_string.length());
            builder.append(alpha_numeric_string.charAt(character));
        }
        return builder.toString();
    }

    /**
     * FUNCTION :: 바이트를 읽을 수 있는 문자열 형태로 치환
     * @param bytes - 바이트
     * @param digits - 소수점 자리수
     * @return human readable format string
     */
    public static String ByteFormat(double bytes, int digits) {
        String[] dictionary = { "bytes", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB" };
        int index = 0;
        for (index = 0; index < dictionary.length; index++) {
            if (bytes < 1024) {
                break;
            }
            bytes = bytes / 1024;
        }
        return String.format("%." + digits + "f", bytes) + " " + dictionary[index];
    }
}