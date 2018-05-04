package com.asher.oes.exception;

import java.util.HashMap;
import java.util.Map;

public class ParameterException extends Exception{

    private static final long serialVersionUID = 8879968783585345498L;

    Map<String,String> errorTip = new HashMap<String, String>();

    public Map<String, String> getErrorTip() {
        return errorTip;
    }

    public void setErrorTip(Map<String, String> errorTip) {
        this.errorTip = errorTip;
    }

    public void addErrorTip(String field, String message) {
        errorTip.put(field, message);
    }

    public boolean hasException() {
        return !errorTip.isEmpty();
    }
}