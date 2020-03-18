package com.example.planner.model;

import com.example.planner.dto.SubTaskDTO;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Task implements Serializable {
    private String description, title;

    @Id
    private String id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate deadlineDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime deadlineTime;
    @OneToMany(targetEntity = SubTask.class, mappedBy ="superTask",fetch = FetchType.EAGER)
    private List<SubTask> subTasks;

    public Task() {
    }

    public Task(String description, String title, LocalDate deadlineDate) {
        this.description = description;
        this.title = title;
        this.deadlineDate = deadlineDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Task(String description, LocalDate deadlineDate, LocalTime deadlineTime, String id, String title) {
        setDescription(description);
        setDeadlineDate(deadlineDate);
        setDeadlineTime(deadlineTime);
        setId(id);
        setTitle(title);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(LocalDate deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public LocalTime getDeadlineTime() {
        return deadlineTime;
    }

    public void setDeadlineTime(LocalTime deadlineTime) {
        this.deadlineTime = deadlineTime;
    }

    public String getFormatted(){
        String pattern = "dd MMMM yyyy";
        DateTimeFormatter s = DateTimeFormatter.ofPattern(pattern);
        String pattern2 = "HH:mm";
        DateTimeFormatter s2 = DateTimeFormatter.ofPattern(pattern2);
        return this.title + ": due " + this.deadlineDate.format(s) +" at "+ this.deadlineTime.format(s2);
    }

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTask> subTasks) {
        this.subTasks = subTasks;
    }

    public void addSubTask(SubTask subTask) {
        if (this.subTasks == null) {
            this.subTasks = new ArrayList<>();
        }
        this.subTasks.add(subTask);
    }
}
