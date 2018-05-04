package com.asher.oes.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.asher.oes.Constants;
import com.asher.oes.util.StringUtil;

public class SessionFilter implements Filter {
    private String notNeedLogin =",page/login/login,page/login/logintest";
    protected FilterConfig filterConfig;

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
        String uri =  req.getRequestURI();
        String requestedUri = uri.substring(req.getContextPath().length() + 1);

        String [] pages = notNeedLogin.split(",");
        boolean isLogin = false;
        for(String page : pages) {
            if (page.equals(requestedUri)) {
                isLogin = true;
                break;
            }
        }

        if (isLogin) {
            chain.doFilter(req, response);
        } else{
            HttpSession session = req.getSession();
            if (session.getAttribute(Constants.USER) == null) {
                if (req.getMethod().toLowerCase().equals("get")) {
                    String queryString = req.getQueryString();
                    String go = requestedUri;
                    if (!StringUtil.isNull(queryString)) {
                        go = go + "#" + req.getQueryString();
                    }
                    resp.sendRedirect(req.getContextPath() + "/page/login/login?go=" + go);
                }else {
                    resp.sendRedirect(req.getContextPath() + "/page/login/login");
                }
            } else {
                chain.doFilter(req, response);
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        if (filterConfig.getInitParameter("notNeedLogin") != null) {
            filterConfig.getInitParameter(notNeedLogin);
        }
    }
}