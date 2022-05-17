package com.moonkii.study.domain;

// Entity (Domain)
// DB의 Entity 와 다름에 주의!
public class Task {
  // Identifier - Unique Key
  private Long id;

  private String title;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String toString() {
    return "Task - title: " + title;
  }
}
