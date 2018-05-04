package com.asher.oes;

import java.util.HashMap;
import java.util.Map;

import com.asher.oes.model.User;

public class AppContext {
    private static final ThreadLocal<AppContext> appContexts = new ThreadLocal<AppContext>();

    private final Map<String, Object> datas = new HashMap<String, Object>();

    private static String contextPath;

    public static String getContextPath() {
        return contextPath;
    }

    public static void setContextPath(String contextPaths) {
        if (AppContext.contextPath == null) {
            AppContext.contextPath = contextPaths;
        }
    }

    private AppContext() {};

    public static AppContext getContext() {
        AppContext context = appContexts.get();
        if (context == null) {
            context = new AppContext();
            appContexts.set(context);
        }
        return context;
    }

    public void clear() {
        AppContext context = appContexts.get();
        if (context != null) {
            context.datas.clear();
        }
        context = null;
    }

    public void addObject(String key, Object value) {
        datas.put(key, value);
    }

    public Object getObject(String key) {
        return datas.get(key);
    }

    public void removeObject(String key) {
        datas.remove(key);
    }

    public User getUser() {
        return (User)datas.get(Constants.APP_CONTEXT_USER);
    }
}