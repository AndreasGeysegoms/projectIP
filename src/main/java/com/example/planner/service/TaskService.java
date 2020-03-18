package com.example.planner.service;

import com.example.planner.db.SubTaskDB;
import com.example.planner.db.TaskDB;
import com.example.planner.dto.SubTaskDTO;
import com.example.planner.dto.TaskDTO;
import com.example.planner.model.SubTask;
import com.example.planner.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService implements com.example.planner.service.Service {

    private final TaskDB taskDB;
    private final SubTaskDB subTaskDB;

    @Autowired
    public TaskService(TaskDB taskDB, SubTaskDB subTaskDB) {
        this.taskDB = taskDB;
        this.subTaskDB = subTaskDB;
    }

    public List<TaskDTO> getTasks() {
        return this.taskDB.findAll().stream().map( t -> {
            TaskDTO task = new TaskDTO();
            task.setId(t.getId());
            task.setDeadlineDate(t.getDeadlineDate());
            task.setDeadlineTime(t.getDeadlineTime());
            task.setDescription(t.getDescription());
            task.setTitle(t.getTitle());

            List<SubTask> subs = t.getSubTasks();
            List<SubTaskDTO> dtosubs = new ArrayList<>();
            for (SubTask s : subs) {
                SubTaskDTO dto = new SubTaskDTO();
                dto.setDescription(s.getDescription());
                dto.setTitle(s.getTitle());
                dto.setId(s.getId());
                dto.setSuperTask(task);
                dtosubs.add(dto);
            }
            task.setSubTasks(dtosubs);
            return task;
        }).collect(Collectors.toList());
    }

    public TaskDTO getTask(String id) {
        Task t = this.taskDB.findById(id).get();
        TaskDTO task = new TaskDTO();
        task.setId(t.getId());
        task.setDeadlineDate(t.getDeadlineDate());
        task.setDeadlineTime(t.getDeadlineTime());
        task.setDescription(t.getDescription());
        task.setTitle(t.getTitle());
        List<SubTask> subs = t.getSubTasks();
        List<SubTaskDTO> dtosubs = new ArrayList<>();
        for (SubTask s : subs) {
            SubTaskDTO dto = new SubTaskDTO();
            dto.setDescription(s.getDescription());
            dto.setTitle(s.getTitle());
            dto.setId(s.getId());
            dto.setSuperTask(task);
            dtosubs.add(dto);
        }
        task.setSubTasks(dtosubs);
        return task;
    }

    public void addTask(TaskDTO t) {
        Task task = new Task();
        task.setId(t.getId());
        task.setDeadlineDate(t.getDeadlineDate());
        task.setDeadlineTime(t.getDeadlineTime());
        task.setDescription(t.getDescription());
        task.setTitle(t.getTitle());
        taskDB.save(task);
    }

    public void editTask(String id, TaskDTO task) {
        Task t = this.taskDB.findById(id).get();
        t.setDeadlineDate(task.getDeadlineDate());
        t.setDeadlineTime(task.getDeadlineTime());
        t.setDescription(task.getDescription());
        t.setTitle(task.getTitle());
        taskDB.saveAndFlush(t);
    }

    public String getLastId() {
        List<TaskDTO> tasks = this.taskDB.findAll().stream().map( t -> {
            TaskDTO task = new TaskDTO();
            task.setId(t.getId());
            task.setDeadlineDate(t.getDeadlineDate());
            task.setDeadlineTime(t.getDeadlineTime());
            task.setDescription(t.getDescription());
            task.setTitle(t.getTitle());
            return task;
        }).collect(Collectors.toList());

        String id = "0";

        for (TaskDTO t: tasks) {
            if (Integer.parseInt(id) < Integer.parseInt(t.getId())) {
                id = t.getId();
            }
        }

        return String.valueOf(Integer.parseInt(id)+1);
    }

    public void addSubTask(SubTaskDTO subdto) {
        String id = subdto.getId();
        SubTask sub = new SubTask();
        sub.setTitle(subdto.getTitle());
        sub.setDescription(subdto.getDescription());

        TaskDTO task = new TaskDTO();
        Task t = this.taskDB.findById(id).get();
        task.setId(t.getId());
        task.setDeadlineDate(t.getDeadlineDate());
        task.setDeadlineTime(t.getDeadlineTime());
        task.setDescription(t.getDescription());
        task.setTitle(t.getTitle());
        List<SubTask> subs = t.getSubTasks();
        List<SubTaskDTO> dtosubs = new ArrayList<>();
        for (SubTask s : subs) {
            SubTaskDTO dto = new SubTaskDTO();
            dto.setDescription(s.getDescription());
            dto.setTitle(s.getTitle());
            dto.setId(s.getId());
            dto.setSuperTask(task);
            dtosubs.add(dto);
        }
        dtosubs.add(subdto);
        subdto.setSuperTask(task);
        sub.setSuperTask(t);
        task.setSubTasks(dtosubs);

        //this.taskDB.save(t);
        //this.subTaskDB.save(sub);
    }

    private String getSubTaskId(String id) {
        String idRes = "0";

        Task t = this.taskDB.findById(id).get();
        for (SubTask s : t.getSubTasks()) {
            if (Integer.parseInt(idRes) < Integer.parseInt(t.getId())) idRes = t.getId();
        }
        return "s"+String.valueOf(Integer.parseInt(idRes)+1);
    }
}
