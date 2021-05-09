// TODO
// 1. Read Collection - GET /tasks
// 2. Read Item - GET /tasks/{id}
// 3. Create - POST /tasks
// 4. Update - PUT/PATCH tasks/{id}
// 5. DELETE - DELETE /tasks/{id}

package com.moonkii.study.controllers;

import com.moonkii.study.models.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
  private List<Task> tasks = new ArrayList<>();
  private Long newId = 0L;

  @GetMapping
  public List<Task> list() {
    System.out.println("GET tasks");
    return tasks;
  }

  @PostMapping
  public Task create(@RequestBody Task task) {
    task.setId(generateId());
    tasks.add(task);

    return task;
  }

  private Long generateId() {
    newId += 1;
    return newId;
  }
}
