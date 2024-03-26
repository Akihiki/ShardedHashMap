package org.example.utils;


public enum StatusCode {
    OK(200),
    CREATED(201),
    ACCEPTED(202),

    BAD_REQUEST(400),
    INTERNAL_SERVER_ERROR(500),
    METHOD_NOT_ALLOWED(405);

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    StatusCode(int code) {
        this.code = code;
    }

    private int code;
}