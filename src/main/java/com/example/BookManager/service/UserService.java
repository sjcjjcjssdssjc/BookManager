package com.example.BookManager.service;

import com.example.BookManager.dao.UserDAO;
import com.example.BookManager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDAO userDao;

    public int addUser(User user){
        return userDao.addUser(user);
    }
    public User getUser(String email){
        return userDao.selectByEmail(email);
    }
    public User getUser(int uid){
        return userDao.selectById(uid);
    }
}