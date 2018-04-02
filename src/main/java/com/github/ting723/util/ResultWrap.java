package com.github.ting723.util;

/**
 * 返回结果
 * @param <T>
 */
public class ResultWrap<T> {

    private String status;

    private static final String SUCCESS = "200";
    private static final String FAILURE = "500";

    private T result;

    private String message;

    public ResultWrap(String status, T result, String message) {
        this.status = status;
        this.result = result;
        this.message = message;
    }

    public static <T> ResultWrap SUCCESS() {
        return SUCCESS("");
    }

    public static <T> ResultWrap SUCCESS(T result) {
        return new ResultWrap<>(SUCCESS, result, "success");
    }


    public static <T> ResultWrap SUCCESS(T result, String message) {
        return new ResultWrap<>(SUCCESS, result, message);
    }

    public static <T> ResultWrap FAILURE() {
        return FAILURE("");
    }

    public static <T> ResultWrap FAILURE(T result) {
        return new ResultWrap<>(FAILURE, result, "服务异常");
    }

    public static <T> ResultWrap FAILURE(String message) {
        return new ResultWrap<>(FAILURE, null, message);
    }
    public static <T> ResultWrap FAILURE(T result, String message) {
        return new ResultWrap<>(FAILURE, result, message);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
