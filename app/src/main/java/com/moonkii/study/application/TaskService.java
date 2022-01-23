package com.moonkii.study.application;

import com.moonkii.study.TaskNotFoundException;
import com.moonkii.study.models.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
  // 1. list -> getTasks
  // 2. detail -> getTask (with ID)
  // 3. create -> createTask (with source)
  // 4. update -> updateTask (with ID, source)
  // 5. delete -> deleteTask (with ID)

  private List<Task> tasks = new ArrayList<>();
  private Long newId = 0L;

  public List<Task> getTasks() {
    return tasks;
  }

  public Task getTask(Long id) {
    return tasks.stream()
        .filter(task -> task.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new TaskNotFoundException(id));
  }

  public Task createTask(Task source) {
    Task task = new Task();
    task.setId(generateId());
    task.setTitle(source.getTitle());

    tasks.add(task);

    return task;
  }

  public Task updateTask(Long id, Task source) {
    Task task = getTask(id);
    task.setTitle(source.getTitle());

    return task;
  }

  public Task deleteTask(Long id) {
    Task task = getTask(id);
    tasks.remove(task);

    return task;
  }

  private Long generateId() {
    newId += 1;
    return newId;
  }

}
