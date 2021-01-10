package com.example.BookManager.service;

import com.example.BookManager.model.User;
import com.example.BookManager.utils.ConcurrentUtils;

public class HostHolder {

    public User getUser(){
        return ConcurrentUtils.getHost();
    }
    public void setUser(User user){
        ConcurrentUtils.setHost(user);
    }
}
