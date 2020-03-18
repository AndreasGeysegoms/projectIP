package com.example.planner.service;

import com.example.planner.dto.SubTaskDTO;
import com.example.planner.dto.TaskDTO;

import java.util.List;

public interface Service {

    List<TaskDTO> getTasks();

    TaskDTO getTask(String id);

    void addTask(TaskDTO task);

    void editTask(String id, TaskDTO task);

    String getLastId();

    void addSubTask(SubTaskDTO subTask);
}
