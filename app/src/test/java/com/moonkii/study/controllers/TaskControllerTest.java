package com.moonkii.study.controllers;

import com.moonkii.study.TaskNotFoundException;
import com.moonkii.study.application.TaskService;
import com.moonkii.study.domain.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class TaskControllerTest {
  TaskController controller;

  // 가능한 것
  // 1. Real object
  // 2. Mock object
  // 3. Spy -> Proxy
  TaskService taskService;

  @BeforeEach
  void setup() {
//    taskService = spy(new TaskService());
    taskService = mock(TaskService.class);

    List<Task> tasks = new ArrayList<>();
    Task task = new Task();
    task.setTitle("Test");
    tasks.add(task);

    given(taskService.getTasks()).willReturn(tasks);
    given(taskService.getTask(1L)).willReturn(task);
    given(taskService.getTask(100L)).willThrow(new TaskNotFoundException(100L));
    given(taskService.updateTask(eq(100L), any(Task.class)))
        .willThrow(new TaskNotFoundException(100L));
    given(taskService.deleteTask(100L))
        .willThrow(new TaskNotFoundException(100L));

    controller = new TaskController(taskService);
  }

  @Test
  void listWithoutTasks(){
    given(taskService.getTasks()).willReturn(new ArrayList<>());

    // taskService.getTasks
    // Controller -> Spy -> Real Object

    assertThat(controller.list()).isEmpty();

    verify(taskService).getTasks();
  }

  @Test
  void listWithSomeTasks() {
    assertThat(controller.list()).isNotEmpty();

    verify(taskService).getTasks();
  }

  @Test
  void detailWithExistedID() {
    Task task = controller.detail(1L);

    assertThat(task).isNotNull();
  }

  @Test
  void detailWithNotExistedID() {
    assertThatThrownBy(() -> controller.detail(100L))
        .isInstanceOf(TaskNotFoundException.class);
  }

  @Test
  void createNewTask() {
    Task task = new Task();
    task.setTitle("Test2");

    controller.create(task);

    verify(taskService).createTask(task);
  }

  @Test
  void updateTaskWithExistedTask() {
    Task task = new Task();
    task.setTitle("Renamed task");

    controller.update(1L, task);

    verify(taskService).updateTask(1L, task);
  }

  @Test
  void updateTaskWithNotExistedTask() {
    Task task = new Task();
    task.setTitle("Renamed task");

    assertThatThrownBy(() -> controller.update(100L, task))
        .isInstanceOf(TaskNotFoundException.class);
  }

  @Test
  void deleteTaskWithExistedTask() {
    controller.delete(1L);

    verify(taskService).deleteTask(1L);
  }

  @Test
  void deleteTaskWithNotExistedTask() {
    Task task = new Task();
    task.setTitle("Renamed task");

    assertThatThrownBy(() -> controller.delete(100L))
        .isInstanceOf(TaskNotFoundException.class);
  }
}
