package com.moonkii.study.application;

import com.moonkii.study.domain.Task;
import com.moonkii.study.domain.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
  // 1. list -> getTasks
  // 2. detail -> getTask (with ID)
  // 3. create -> createTask (with source)
  // 4. update -> updateTask (with ID, source)
  // 5. delete -> deleteTask (with ID)

  private final TaskRepository taskRepository;

  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public List<Task> getTasks() {
    return taskRepository.findAll();
  }

  public Task getTask(Long id) {
    return taskRepository.find(id);
  }

  public Task createTask(Task source) {
    Task task = new Task();
    task.setTitle(source.getTitle());

    return taskRepository.save(task);
  }

  public Task updateTask(Long id, Task source) {
    Task task = taskRepository.find(id);

    task.setTitle(source.getTitle());

    return task;
  }

  public Task deleteTask(Long id) {
    Task task = taskRepository.find(id);

    return taskRepository.remove(task);
  }
}
