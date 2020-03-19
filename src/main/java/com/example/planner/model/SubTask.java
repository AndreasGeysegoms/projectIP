package com.example.planner.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class SubTask implements Serializable {

    @ManyToOne
    private Task superTask;
    private String title;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String description;

    public SubTask() {
    }

    public SubTask(String title, String description, Task superTask) {
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

    public SubTask(String title, String description) {
        setDescription(description);
        setTitle(title);
    }

    public Task getSuperTask() {
        return superTask;
    }

    public void setSuperTask(Task superTask) {
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