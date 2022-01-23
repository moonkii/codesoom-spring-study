package com.moonkii.study.controllers;

import com.moonkii.study.models.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TaskControllerTest {
  TaskController controller;

  @BeforeEach
  void setup() {
    controller = new TaskController();
  }

  @Test
  void list() {
    assertThat(controller.list()).isEmpty();
  }

  @Test
  void createNewTask() {
    Task task = new Task();

    task.setTitle("Test1");
    controller.create(task);

    task.setTitle("Test2");
    controller.create(task);

    assertThat(controller.list()).hasSize(2);
    assertThat(controller.list().get(0).getId()).isEqualTo(1L);
    assertThat(controller.list().get(0).getTitle()).isEqualTo("Test1");
    assertThat(controller.list().get(1).getId()).isEqualTo(2L);
    assertThat(controller.list().get(1).getTitle()).isEqualTo("Test2");
  }

}
