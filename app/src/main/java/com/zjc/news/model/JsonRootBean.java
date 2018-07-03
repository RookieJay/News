package com.zjc.news.model;

import java.util.List;

public class JsonRootBean {

    private List<Result> result;
    private int error_code;
    private String reason;
    public void setResult(List<Result> result) {
        this.result = result;
    }
    public List<Result> getResult() {
        return result;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }
    public int getError_code() {
        return error_code;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getReason() {
        return reason;
    }

}