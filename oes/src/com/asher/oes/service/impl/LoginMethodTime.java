package com.asher.oes.service.impl;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

import com.asher.oes.AppContext;
import com.asher.oes.model.User;

public class LoginMethodTime implements MethodInterceptor{

    private final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {

        long startTime = System.currentTimeMillis();
        Object object = mi.proceed();
        String methodName = mi.getMethod().getName();
        long endTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        User user = (User)AppContext.getContext().getObject("APP_CONTEXT_USER");
        if (user != null) {
          sb.append(user.getUserName());
        }
        sb.append(":");
        sb.append(mi.getMethod().getDeclaringClass().getSimpleName());
        sb.append(":");
        sb.append(methodName);
        sb.append(":");
        sb.append(endTime - startTime);

        logger.info(sb);
        return object;
    }
}