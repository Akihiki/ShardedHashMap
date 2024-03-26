package org.example.utils;

import com.sun.net.httpserver.Headers;
import org.example.utils.StatusCode;

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
