package com.example.BookManager.utils;

import com.example.BookManager.model.User;

public class ConcurrentUtils {
    /*
    ThreadLocal提供了线程的局部变量，每个线程都可以通过set()和get()来对这个
    局部变量进行操作，但不会和其他线程的局部变量进行冲突，实现了线程的数据隔离～。
     */
    private static ThreadLocal<User> host = new ThreadLocal<>();
    public static User getHost(){
        return host.get();
    }
    public static void setHost(User user){
        host.set(user);
    }
}
