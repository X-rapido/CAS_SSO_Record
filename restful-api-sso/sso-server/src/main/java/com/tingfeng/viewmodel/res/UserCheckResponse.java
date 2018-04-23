package com.tingfeng.viewmodel.res;

public class UserCheckResponse {

    private int status = 0; // 0：fail，1：success
    private String data = "nothing";

    public UserCheckResponse() {
    }

    public UserCheckResponse(int status, String data) {
        this.status = status;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
