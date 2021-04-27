package com.moonkii.study;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moonkii.study.models.Task;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DemoHttpHandler implements HttpHandler {
  private ObjectMapper objectMapper = new ObjectMapper();

  private List<Task> tasks = new ArrayList<>();

  public DemoHttpHandler() {
    Task task = new Task();
    task.setId(1L);
    task.setTitle("Do nothing...");
    tasks.add(task);
  }

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    // 요청의 3요소
    // 1. Method - GET, POST, PUT/PATCH ,DELETE ...
    // 2. Path - "/", "/tasks", "/tasks/1" ...
    // 3. Headers, Body(Content)

    String method = exchange.getRequestMethod();
    URI uri = exchange.getRequestURI();
    String path = uri.getPath();

    InputStream inputStream = exchange.getRequestBody();
    String body = new BufferedReader(new InputStreamReader(inputStream))
        .lines()
        .collect(Collectors.joining("\n"));

    System.out.println(method + " " + path);

    if (!body.isBlank()) {
      Task task = toTask(body);
      tasks.add(task);
    }

    String contents = "Hello, World!";

    if (method.equals("GET") && path.equals("/tasks")) {
      contents = tasksToJSON();
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

  private Task toTask(String content) throws JsonProcessingException {
    return objectMapper.readValue(content, Task.class);
  }

  private String tasksToJSON() {
    OutputStream outputStream = new ByteArrayOutputStream();

    try {
      objectMapper.writeValue(outputStream, tasks);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return outputStream.toString();
  }
}
