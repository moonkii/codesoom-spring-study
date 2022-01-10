// TODO
// 1. Read Collection - GET /tasks
// 2. Read Item - GET /tasks/{id}
// 3. Create - POST /tasks
// 4. Update - PUT/PATCH tasks/{id}
// 5. DELETE - DELETE /tasks/{id}

package com.moonkii.study.controllers;

import com.moonkii.study.application.TaskService;
import com.moonkii.study.models.Task;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin
public class TaskController {
  private TaskService taskService;

  public TaskController() {
    taskService = new TaskService();
  }

  @GetMapping
  public List<Task> list() {
    return taskService.getTasks();
  }

  @GetMapping("{id}")
  public Task detail(@PathVariable Long id) {
    return taskService.getTask(id);
  }

  @PutMapping("{id}")
  public Task update(@PathVariable Long id, @RequestBody Task source) {
    return taskService.updateTask(id, source);
  }

  @PatchMapping("{id}")
  public Task patch(@PathVariable Long id, @RequestBody Task source) {
    return taskService.updateTask(id, source);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    taskService.deleteTask(id);
  }

  @PostMapping
  public Task create(@RequestBody Task task) {
    return taskService.createTask(task);
  }
}
