package com.moonkii.study.controllers;

import com.moonkii.study.TaskNotFoundException;
import com.moonkii.study.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TaskErrorAdvice {
  @ResponseBody
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(TaskNotFoundException.class)
  public ErrorResponse handleNotFound() {
    return new ErrorResponse("Task not found!");
  }
}
