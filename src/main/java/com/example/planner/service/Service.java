package com.example.planner.service;

import com.example.planner.dto.SubTaskDTO;
import com.example.planner.dto.TaskDTO;

import java.util.List;

public interface Service {

    List<TaskDTO> getTasks();

    TaskDTO getTask(int id);

    void addTask(TaskDTO task);

    void editTask(int id, TaskDTO task);

    void addSubTask(SubTaskDTO subTask);
}
