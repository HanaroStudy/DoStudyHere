package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import org.example.http.request.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {
    private static final int DEFAULT_PORT = 8080;
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(DEFAULT_PORT)) {
            logger.info("Listen on {}:{}", serverSocket.getInetAddress(), serverSocket.getLocalPort());
            Socket socket = null;
            while (true) {
                try {
                    socket = serverSocket.accept();
                    logger.info("Client connected : {}:{}", socket.getInetAddress(), socket.getPort());

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    HttpRequest httpRequest = null;
                    try {
                        httpRequest = HttpRequest.from(bufferedReader);
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                    if (httpRequest != null) {
                        logger.info("Request URL : {}", httpRequest.getRequestUrl());
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage());
                } finally {
                    logger.info("CLOSE Socket ...");
                    if (socket != null) {
                        socket.close();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}