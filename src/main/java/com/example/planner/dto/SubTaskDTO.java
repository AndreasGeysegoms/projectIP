package com.example.planner.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class SubTaskDTO {
    private TaskDTO superTask;
    @NotEmpty
    @Size(min=1)
    private String title;
    @NotNull
    private int id;
    @NotEmpty
    @Size(min=1)
    private String description;

    public SubTaskDTO() {
    }

    public SubTaskDTO(String title, String description, TaskDTO superTask) {
        setTitle(title);
        setDescription(description);
        setSuperTask(superTask);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SubTaskDTO(String title, String description) {
        setDescription(description);
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