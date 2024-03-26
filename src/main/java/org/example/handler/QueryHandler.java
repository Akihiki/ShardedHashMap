package org.example.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import org.example.controller.HashMapAccessController;
import org.example.dto.HashMapElementDTO;
import org.example.exeptions.GenericException;
import org.example.utils.ResponseEntity;
import org.example.utils.StatusCode;
import org.example.repository.HashMapRepository;
import org.example.utils.Utils;

import java.io.*;
import java.sql.SQLException;

public class QueryHandler {
    HashMapAccessController hashMapAccessController = new HashMapAccessController();
    ExceptionHandler exceptionHandler=new ExceptionHandler();

    public void handle(HttpExchange httpExchange) {
        try {
            if ("POST".equals(httpExchange.getRequestMethod())) {
                HashMapElementDTO hashMapElementDTO = Utils.readBody(httpExchange.getRequestBody());
                hashMapAccessController.saveElement(hashMapElementDTO, httpExchange);
            } else if ("GET".equals(httpExchange.getRequestMethod())) {
                String key = Utils.getKeyFromPath(httpExchange.getRequestURI().getPath());
                hashMapAccessController.getElement(key, httpExchange);
            } else if ("DELETE".equals(httpExchange.getRequestMethod())) {
                String key = Utils.getKeyFromPath(httpExchange.getRequestURI().getPath());
                hashMapAccessController.deleteElement(key, httpExchange);
            } else throw new GenericException("Method not allowed", StatusCode.METHOD_NOT_ALLOWED);
        } catch (GenericException genericException) {
            try {
                exceptionHandler.handleGenericException(genericException,httpExchange);
            }catch (GenericException genericException1){
                System.out.println("Even the handler threw the exception 😅");
            }
        }
    }


}
