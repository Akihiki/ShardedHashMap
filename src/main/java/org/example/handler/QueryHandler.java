package org.example.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import org.example.RequestType;
import org.example.ResponseEntity;
import org.example.StatusCode;
import org.example.repository.HashMapRepository;
import org.example.repository.HashMapRepositoryImpl;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class QueryHandler {
    byte[] response;
    ObjectMapper objectMapper=new ObjectMapper();
    HashMapRepository hashMapRepository=new HashMapRepositoryImpl();

    public void handle(HttpExchange httpExchange) throws IOException {
        if ("POST".equals(httpExchange.getRequestMethod())) {
            System.out.println("kkk");
            RequestType requestType=objectMapper.readValue(httpExchange.getRequestBody(), RequestType.class);
            System.out.println("hi");
            //Traitement de database ;
            try {
                System.out.println("hi");
                hashMapRepository.post(requestType.getKey(),requestType.getValue());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            ResponseEntity e = new ResponseEntity("done",
                    getHeaders("Content-Type", "application/json"), StatusCode.OK);
            httpExchange.getResponseHeaders().putAll(e.headers());
            httpExchange.sendResponseHeaders(e.statusCode().getCode(), 0);
            response = objectMapper.writeValueAsBytes(e.body());
        } else if ("GET".equals(httpExchange.getRequestMethod())){
            System.out.println("get "+httpExchange.getRequestURI().getPath());
            String a=httpExchange.getRequestURI().getPath().split("/")[2];
            String result="";
            try {
               result= hashMapRepository.get(a);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //Traitement de database ;
            ResponseEntity e = new ResponseEntity("result : "+result,
                    getHeaders("Content-Type", "application/json"), StatusCode.OK);
            httpExchange.getResponseHeaders().putAll(e.headers());
            httpExchange.sendResponseHeaders(e.statusCode().getCode(), 0);
            response = objectMapper.writeValueAsBytes(e.body());
        }else if ("DELETE".equals(httpExchange.getRequestMethod())){
            System.out.println("delete"+httpExchange.getRequestURI().getPath());
            String a=httpExchange.getRequestURI().getPath().split("/")[2];
            //Traitement de database ;
            try {
                hashMapRepository.delete(a);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            ResponseEntity e = new ResponseEntity("done",
                    getHeaders("Content-Type", "application/json"), StatusCode.OK);
            httpExchange.getResponseHeaders().putAll(e.headers());
            httpExchange.sendResponseHeaders(e.statusCode().getCode(), 0);
            response = objectMapper.writeValueAsBytes(e.body());
        }

        OutputStream os = httpExchange.getResponseBody();
        os.write(response);
        os.close();
    }

    protected static Headers getHeaders(String key, String value) {
        Headers headers = new Headers();
        headers.set(key, value);
        return headers;
    }



}
