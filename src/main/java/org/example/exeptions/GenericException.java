package org.example.exeptions;

import org.example.utils.StatusCode;

public class GenericException extends Exception{
    StatusCode statusCode;

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public GenericException(String message, StatusCode statusCode) {
        super(message);
        this.statusCode=statusCode;
    }
}
