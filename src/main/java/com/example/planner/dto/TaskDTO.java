package com.example.planner.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TaskDTO {

    private String description, title;
    private String id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate deadlineDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime deadlineTime;

    private List<SubTaskDTO> subTasks;

    public TaskDTO() {
    }

    public TaskDTO(String description, String title, LocalDate deadlineDate) {
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

    public TaskDTO(String description, LocalDate deadlineDate, LocalTime deadlineTime, String id, String title) {
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

    public List<SubTaskDTO> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTaskDTO> subTasks) {
        this.subTasks = subTasks;
    }

    public void addSubTask(SubTaskDTO subTaskDTO) {
        if (this.subTasks == null) {
            this.subTasks = new ArrayList<>();
        }
        this.subTasks.add(subTaskDTO);
    }
}
