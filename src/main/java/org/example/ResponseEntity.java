package org.example;

import com.sun.net.httpserver.Headers;

import java.io.InputStream;

public record ResponseEntity(String body, Headers headers, StatusCode statusCode) {
    @Override
    public String body() {
        return body;
    }

    @Override
    public Headers headers() {
        return headers;
    }

    @Override
    public StatusCode statusCode() {
        return statusCode;
    }
}
