package com.example.planner.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class SubTask implements Serializable {

    @ManyToOne
    private Task superTask;
    @Id
    private String title;
    private String idSuper;
    private String description;

    public SubTask() {
    }

    public SubTask(String title, String description, Task superTask) {
        setTitle(title);
        setDescription(description);
        setSuperTask(superTask);
    }

    public String getId() {
        return idSuper;
    }

    public void setId(String idSuper) {
        this.idSuper = idSuper;
    }

    public SubTask(String title, String idSuper, String description) {
        setDescription(description);
        setId(idSuper);
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
