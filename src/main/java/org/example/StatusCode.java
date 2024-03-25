package org.example;


public enum StatusCode {
    OK(200),
    CREATED(201),
    ACCEPTED(202),

    BAD_REQUEST(400),
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