package com.asher.oes.dao;

import com.asher.oes.model.User;

public interface UserDao {

    public User getUser(String userName);
    public int updateUserLoginTime(User user);

}