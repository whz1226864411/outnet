package com.creat.outnet.po;

import java.io.Serializable;

/**
 * Created by whz on 2017/10/1.
 */
public class Message implements Serializable{
    private String sessionId;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
