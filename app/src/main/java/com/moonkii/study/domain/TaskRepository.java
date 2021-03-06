package com.moonkii.study.domain;

import com.moonkii.study.TaskNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {
  private List<Task> tasks = new ArrayList<>();
  private Long newId = 0L;

  public List<Task> findAll() {
    return null;
  }

  public Task find(Long id) {
    return tasks.stream()
        .filter(task -> task.getId().equals(id))
        .findFirst()
        .orElseThrow(() -> new TaskNotFoundException(id));

  }

  public Task save(Task task) {
    task.setId(generateId());

    tasks.add(task);

    return task;
  }

  public Task remove(Task task) {
    tasks.remove(task);

    return task;
  }

  private Long generateId() {
    newId += 1;
    return newId;
  }
}
