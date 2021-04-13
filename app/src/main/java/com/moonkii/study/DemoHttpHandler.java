package com.moonkii.study;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;

public class DemoHttpHandler implements HttpHandler {
  @Override
  public void handle(HttpExchange exchange) throws IOException {
    // 요청의 3요소
    // 1. Method - GET, POST, PUT/PATCH ,DELETE ...
    // 2. Path - "/", "/tasks", "/tasks/1" ...
    // 3. Headers, Body(Content)

    String method = exchange.getRequestMethod();
    URI uri = exchange.getRequestURI();
    String path = uri.getPath();
    System.out.println(method + " " + path);

    String contents = "Hello, World!";

    if (method.equals("GET") && path.equals("/tasks")) {
      contents = "No task";
    }

    if (method.equals("POST") && path.equals("/tasks")) {
      contents = "Create a task";
    }

    exchange.sendResponseHeaders(200, contents.getBytes().length);

    OutputStream outputStream = exchange.getResponseBody();
    outputStream.write(contents.getBytes());
    outputStream.flush();
    outputStream.close();
  }
}
