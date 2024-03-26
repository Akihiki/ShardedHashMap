package org.example.handler;

import com.sun.net.httpserver.HttpExchange;
import org.example.exeptions.GenericException;
import org.example.utils.ResponseEntity;
import org.example.utils.StatusCode;
import org.example.utils.Utils;

import java.io.IOException;
import java.io.OutputStream;

public class ExceptionHandler {
    byte[] response;
    public  void handleGenericException(GenericException genericException, HttpExchange httpExchange) throws GenericException {

        ResponseEntity e = new ResponseEntity(genericException.getMessage(),
                Utils.getHeaders("Content-Type", "application/json"), genericException.getStatusCode());
        response = Utils.prepareResponse(httpExchange, e);
        OutputStream os = httpExchange.getResponseBody();
        try {
            os.write(response);
            os.close();
        } catch (IOException ex) {
            throw new GenericException("Internal Server Error : when processing OutputStream to send response", StatusCode.INTERNAL_SERVER_ERROR);
        }
    }

}
