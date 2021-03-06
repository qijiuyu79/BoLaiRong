package com.zxdc.utils.library.bean;

import java.io.Serializable;

/**
 * 网络请求状态实体类
 */

public class BaseBean implements Serializable{
    private int code;
    private String msg;
    private String token;

    public BaseBean(){}

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isSussess() {
        if (code == 0) {
            return true;
        }
        return false;
    }

}
