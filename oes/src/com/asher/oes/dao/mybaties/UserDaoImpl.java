package com.asher.oes.dao.mybaties;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.asher.oes.dao.UserDao;
import com.asher.oes.model.User;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

    public static final String CLASS_NAME = User.class.getName();
    public static final String SQL_ID_USER_GET_USER = ".getUser";
    public static final String SQL_ID_INT_UPDATE_USER_LOGIN_TIME = ".updateUserLoginTime";

    @Override
    public User getUser(String userName) {
        return getSqlSession().selectOne(CLASS_NAME + SQL_ID_USER_GET_USER, userName);
    }

    @Override
    public int updateUserLoginTime(User user) {
        return getSqlSession().update(CLASS_NAME + SQL_ID_INT_UPDATE_USER_LOGIN_TIME, user);
    }

}