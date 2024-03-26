package org.example;

import com.sun.net.httpserver.HttpServer;
import org.example.handler.QueryHandler;
import org.example.utils.DbConnection;

import java.io.IOException;
import java.net.InetSocketAddress;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Establish connection to our databases (2 in this case for simplicity)
        DbConnection.connect();

        int serverPort = 8000;
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(serverPort), 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        QueryHandler queryHandler=new QueryHandler();
        server.createContext("/api/", queryHandler::handle);
        server.setExecutor(null);
        server.start();
    }
}