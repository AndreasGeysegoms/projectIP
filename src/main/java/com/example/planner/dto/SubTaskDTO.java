package com.example.planner.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

public class SubTaskDTO {
    private TaskDTO superTask;
    private String title;
    private String id;
    private String description;

    public SubTaskDTO() {
    }

    public SubTaskDTO(String title, String description, TaskDTO superTask) {
        setTitle(title);
        setDescription(description);
        setSuperTask(superTask);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SubTaskDTO(String title, String id, String description) {
        setDescription(description);
        setId(id);
        setTitle(title);
    }

    public TaskDTO getSuperTask() {
        return superTask;
    }

    public void setSuperTask(TaskDTO superTask) {
        this.superTask = superTask;
        superTask.addSubTask(this);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
