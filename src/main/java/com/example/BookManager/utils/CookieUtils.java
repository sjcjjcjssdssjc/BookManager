package com.example.BookManager.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
    private static int COOKIE_AGE =60*60*24*7;
    public static void writeCookie(String key,String value, HttpServletResponse response){
        Cookie cookie = new Cookie(key,value);
        cookie.setPath("/");
        //cookie shared in the same server
        cookie.setMaxAge(COOKIE_AGE);
        response.addCookie(cookie);
        //set user's Cookie
    }
    public static String getCookie(String key,HttpServletRequest request){
        //from user
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(key))
                    return cookie.getValue();
            }
        }
        return null;
    }

    public static void removeCookie(String key, HttpServletRequest request,
                                    HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < (cookies == null ? 0 : cookies.length); i++) {
            if (key.equalsIgnoreCase(cookies[i].getName())) {//忽略大小写
                Cookie cookie = new Cookie(key, "");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }
}
