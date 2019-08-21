package com.example.springbootdemo.response;

import java.io.Serializable;

public class ResponseBo <T>  implements Serializable {
    private String resMsg;
    private T result;

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
