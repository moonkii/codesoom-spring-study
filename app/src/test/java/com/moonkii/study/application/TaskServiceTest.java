package com.moonkii.study.application;

import com.moonkii.study.TaskNotFoundException;
import com.moonkii.study.domain.Task;
import com.moonkii.study.domain.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TaskServiceTest {
  private static final String TASK_TITLE = "test";
  private static final String UPDATE_SUFFIX = "test";

  private TaskService taskService;

  @BeforeEach
  void setUp() {
    TaskRepository taskRepository = new TaskRepository();

    // subject(테스트하는 대상)
    taskService = new TaskService(taskRepository);

    // fixtures
    Task task = new Task();
    task.setTitle(TASK_TITLE);
    taskService.createTask(task);
  }

  @Test
  void getTasks() {
    List<Task> tasks = taskService.getTasks();

    assertThat(tasks).hasSize(1);

    Task task = tasks.get(0);
    assertThat(task.getTitle()).isEqualTo(TASK_TITLE);
  }

  @Test
  void getTaskWithValidId() {
    Task task = taskService.getTask(1L);

    assertThat(task.getTitle()).isEqualTo(TASK_TITLE);
  }

  @Test
  void getTaskWithoutValidId() {
    assertThat(taskService.getTask(1L).getTitle()).isEqualTo(TASK_TITLE);

    assertThatThrownBy(() -> taskService.getTask(100L))
        .isInstanceOf(TaskNotFoundException.class);
  }

  @Test
  void createTask() {
    int oldSize = taskService.getTasks().size();

    Task task = new Task();
    task.setTitle(TASK_TITLE);

    taskService.createTask(task);

    int newSize = taskService.getTasks().size();

    assertThat(newSize - oldSize).isEqualTo(1);
  }

  @Test
  void updateTaskWithExistedID() {
    Task source = new Task();
    source.setTitle(TASK_TITLE + UPDATE_SUFFIX);
    taskService.updateTask(1L, source);

    Task task = taskService.getTask(1L);
    assertThat(task.getTitle()).isEqualTo(TASK_TITLE + UPDATE_SUFFIX);
  }

  @Test
  void updateTaskWithNotExistedID() {
    Task source = new Task();
    source.setTitle(TASK_TITLE + UPDATE_SUFFIX);

    assertThatThrownBy(() -> taskService.updateTask(100L, source))
        .isInstanceOf(TaskNotFoundException.class);
  }

  @Test
  void deleteTaskWithExistedID() {
    int oldSize = taskService.getTasks().size();

    taskService.deleteTask(1L);

    int newSize = taskService.getTasks().size();

    assertThat(oldSize - newSize).isEqualTo(1);
  }

  @Test
  void deleteTaskWithNotExistedID() {
    assertThatThrownBy(() -> taskService.deleteTask(100L))
        .isInstanceOf(TaskNotFoundException.class);
  }
}
