package com.asher.oes.service;

import com.asher.oes.exception.ParameterException;
import com.asher.oes.exception.ServiceException;
import com.asher.oes.model.User;

public interface UserService {

    public User login(String userName, String password ) throws ParameterException ,ServiceException;

}
