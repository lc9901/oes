package com.asher.oes.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.asher.oes.AppContext;
import com.asher.oes.Constants;
import com.asher.oes.model.User;
import com.asher.oes.util.PropertyUtil;

public class AppContextFilter implements Filter {

    public AppContextFilter() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;

        AppContext.setContextPath(req.getContextPath());
        AppContext context = AppContext.getContext();
        HttpSession session = req.getSession();
        String staticSource = PropertyUtil.getStaticSource();
        session.setAttribute(Constants.STATIC_SOURCE_ROOT, staticSource);
        context.addObject(Constants.APP_CONTEXT_SESSION, session);
        User user = (User)session.getAttribute(Constants.USER);

        context.addObject(Constants.APP_CONTEXT_USER, user);
        try {
            chain.doFilter(request, response);
        } finally {
            context.clear();
        }
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

}