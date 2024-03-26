package org.example.controller;

import com.sun.net.httpserver.HttpExchange;
import org.example.dto.HashMapElementDTO;
import org.example.exeptions.GenericException;
import org.example.service.HashMapService;
import org.example.utils.ResponseEntity;
import org.example.utils.StatusCode;
import org.example.utils.Utils;

public class HashMapAccessController {
    HashMapService hashMapService = new HashMapService();

    public void saveElement(HashMapElementDTO hashMapElementDTO, HttpExchange httpExchange) throws GenericException {
        if (Utils.validateElementDTO(hashMapElementDTO))
            hashMapService.saveElement(hashMapElementDTO, httpExchange);
    }

    public void getElement(String key, HttpExchange httpExchange) throws GenericException {
        hashMapService.getElement(key, httpExchange);
    }

    public void deleteElement(String key, HttpExchange httpExchange) throws GenericException {
        hashMapService.deleteElement(key, httpExchange);
    }
}
