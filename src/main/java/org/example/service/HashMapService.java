package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import org.example.dto.HashMapElementDTO;
import org.example.exeptions.GenericException;
import org.example.repository.HashMapRepository;
import org.example.utils.ResponseEntity;
import org.example.utils.StatusCode;
import org.example.utils.Utils;

import java.io.IOException;
import java.io.OutputStream;

public class HashMapService {
    byte[] response;

    HashMapRepository hashMapRepository = new HashMapRepository();

    public void saveElement(HashMapElementDTO hashMapElementDTO, HttpExchange httpExchange) throws GenericException {
        int result = 0;

        result = hashMapRepository.saveElement(hashMapElementDTO.getKey(), hashMapElementDTO.getValue());

        if (result == 1) {
            ResponseEntity e = new ResponseEntity("Element saved successfully",
                    Utils.getHeaders("Content-Type", "application/json"), StatusCode.CREATED);
            response = Utils.prepareResponse(httpExchange, e);
        }
        OutputStream os = httpExchange.getResponseBody();
        try {
            os.write(response);
            os.close();
        } catch (IOException e) {
            throw new GenericException("Internal Server Error : when processing OutputStream to send response", StatusCode.INTERNAL_SERVER_ERROR);
        }

    }

    public void getElement(String key, HttpExchange httpExchange) throws GenericException {
        String resultValue = hashMapRepository.get(key);
        ResponseEntity e = new ResponseEntity("Element fetched succefully : " + new HashMapElementDTO(key, resultValue),
                Utils.getHeaders("Content-Type", "application/json"), StatusCode.OK);
        response = Utils.prepareResponse(httpExchange, e);
        OutputStream os = httpExchange.getResponseBody();
        try {
            os.write(response);
            os.close();
        } catch (IOException ex) {
            throw new GenericException("Internal Server Error : when processing OutputStream to send response", StatusCode.INTERNAL_SERVER_ERROR);
        }
    }


    public void deleteElement(String key, HttpExchange httpExchange) throws GenericException {
        int result = 0;

        result = hashMapRepository.deleteElement(key);
        if (result == 1) {
            ResponseEntity e = new ResponseEntity("Element deleted successfully",
                    Utils.getHeaders("Content-Type", "application/json"), StatusCode.OK);
            response = Utils.prepareResponse(httpExchange, e);
        }
        OutputStream os = httpExchange.getResponseBody();
        try {
            os.write(response);
            os.close();
        } catch (IOException e) {
            throw new GenericException("Internal Server Error : when processing OutputStream to send response", StatusCode.INTERNAL_SERVER_ERROR);
        }
    }
}
