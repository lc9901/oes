package com.asher.oes.service.impl;

import org.apache.log4j.Logger;

import com.asher.oes.dao.mybaties.UserDaoImpl;
import com.asher.oes.exception.ParameterException;
import com.asher.oes.exception.ServiceException;
import com.asher.oes.model.User;
import com.asher.oes.service.UserService;
import com.asher.oes.util.StringUtil;

/**
 * UserService
 * @author Asher.Chen
 *
 */
public class UserServiceImpl implements UserService{

    private final Logger logger = Logger.getLogger(UserServiceImpl.class);

    private UserDaoImpl userDao;
    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }
    @Override
    public User login(String userName, String password ) throws ParameterException ,ServiceException {

        ParameterException parameterException = new ParameterException();

        if (StringUtil.isNull(userName)) {
            parameterException.addErrorTip("errorUserName", "UserName is null.");
        }

        if (StringUtil.isNull(password)) {
            parameterException.addErrorTip("errorPassword", "password is null.");
        }

        if (parameterException.hasException()) {
            throw parameterException;
        }

        User user = new User();
        user.setUserName(userName);

        user = userDao.getUser(userName);

        if (user == null) {
            throw new ServiceException(100,"The username is wrong!");
        }

        if (!password.equals(user.getPassword())) {
            throw new ServiceException(101,"The password is wrong!");
        }

        logger.info(userName);
        userDao.updateUserLoginTime(user);
        return user;
    }
}