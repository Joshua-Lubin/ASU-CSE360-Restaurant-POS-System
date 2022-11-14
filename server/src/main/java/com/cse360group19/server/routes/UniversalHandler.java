package com.cse360group19.server.routes;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Pattern;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

public class UniversalHandler implements HttpHandler {

    static final int OK = 200;

    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();

        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("web" + path);
            byte[] file = inputStream.readAllBytes();

            String[] fileExtension = path.split(Pattern.quote("."));

            String contentType = "text/html";

            if(fileExtension.length < 2) {
                throw new Exception();
            }

            switch(fileExtension[fileExtension.length - 1]) {
                case "png":
                    contentType = "image/png";
                    break;
                case "js":
                    contentType = "text/javascript";
                    break;
                case "css":
                    contentType = "text/css";
                    break;
            }

            exchange.getResponseHeaders().set("Content-Type", contentType);

            exchange.sendResponseHeaders(OK, file.length);

            OutputStream stream = exchange.getResponseBody();
            stream.write(file);
            stream.close();
        }
        catch(Exception e) {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("web/index.html");;
            byte[] file = inputStream.readAllBytes();

            exchange.getResponseHeaders().set("Content-Type", "text/html");

            exchange.sendResponseHeaders(OK, file.length);

            OutputStream stream = exchange.getResponseBody();
            stream.write(file);
            stream.close();
        }
    }
}
