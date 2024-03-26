package org.example.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.example.dto.HashMapElementDTO;
import org.example.exeptions.GenericException;

import java.io.IOException;
import java.io.InputStream;

public class Utils {
    static ObjectMapper objectMapper = new ObjectMapper();

    public static Headers getHeaders(String key, String value) {
        Headers headers = new Headers();
        headers.set(key, value);
        return headers;
    }

    public static String getKeyFromPath(String path) throws GenericException {
        String[] splittedPath = path.split("/");
        if (splittedPath.length == 3 && splittedPath[1].equals("api") && splittedPath[2] != null)
            return splittedPath[2];
        else
            throw new GenericException("Please make sure to write a good path for your request as follows : address:port/api/key", StatusCode.BAD_REQUEST);
    }

    public static boolean validateElementDTO(HashMapElementDTO hashMapElementDTO) throws GenericException {
        if (hashMapElementDTO.getKey() != null && hashMapElementDTO.getValue() != null)
            return true;

        else
            throw new GenericException("One or both values you sent are null , please make sure to send valid data", StatusCode.BAD_REQUEST);
    }
    public static byte[] prepareResponse(HttpExchange httpExchange, ResponseEntity e) throws GenericException {
        httpExchange.getResponseHeaders().putAll(e.headers());
        try {
            httpExchange.sendResponseHeaders(e.statusCode().getCode(), 0);
        } catch (IOException ex) {
            throw new GenericException("Internal Server Error : when sending response headers", StatusCode.INTERNAL_SERVER_ERROR);
        }
        try {
            return objectMapper.writeValueAsBytes(e.body());
        } catch (JsonProcessingException ex) {
            throw new GenericException("Internal Server Error : when processing JSON to send response", StatusCode.INTERNAL_SERVER_ERROR);
        }
    }

    public static HashMapElementDTO readBody(InputStream requestBody) throws GenericException {
        try {
            return objectMapper.readValue(requestBody, HashMapElementDTO.class);
        } catch (IOException e) {
            throw new GenericException("Internal Server Error : when reading request body", StatusCode.INTERNAL_SERVER_ERROR);
        }
    }
}
