package com.example.planner.service;

import com.example.planner.dto.SubTaskDTO;
import com.example.planner.dto.TaskDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class TaskServiceTest {
    //integration tests

    @Autowired
    private TaskService service;

    @Test
    public void testGetTasks() {
        List<TaskDTO> tasks = service.getTasks();
        int len = tasks.size();

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("Titel");
        taskDTO.setDescription("Beschrijving");
        taskDTO.setDeadlineDate(LocalDate.of(2021,5,20));
        taskDTO.setDeadlineTime(LocalTime.of(5,19));
        taskDTO.setId(0);
        service.addTask(taskDTO);

        tasks = service.getTasks();

        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());
        assertEquals(len+1, tasks.size());
        TaskDTO task = tasks.get(len);
        assertNotNull(task);
    }

    @Test
    public void testGetTask() {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("Titel");
        taskDTO.setDescription("Beschrijving");
        taskDTO.setDeadlineDate(LocalDate.of(2021,5,20));
        taskDTO.setDeadlineTime(LocalTime.of(5,19));
        taskDTO.setId(1);

        service.addTask(taskDTO);

        TaskDTO task = service.getTask(1);

        assertNotNull(task);
        assertEquals(1, task.getId());
        assertEquals("Titel", task.getTitle());
        assertEquals("Beschrijving",task.getDescription());
        assertEquals(LocalDate.of(2021,5,20), task.getDeadlineDate());
        assertEquals(LocalTime.of(5,19), task.getDeadlineTime());
    }

    @Test
    public void testAddTask() {
        int len = service.getTasks().size();

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("Titel");
        taskDTO.setDescription("Beschrijving");
        taskDTO.setDeadlineDate(LocalDate.of(2021,5,20));
        taskDTO.setDeadlineTime(LocalTime.of(5,19));
        service.addTask(taskDTO);

        List<TaskDTO> tasks = service.getTasks();

        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());
        assertEquals(len+1, tasks.size());
        assertEquals(tasks.get(len).getFormatted(),taskDTO.getFormatted());
    }

    @Test
    public void testEditTask() {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("Titekl");
        taskDTO.setDescription("Beschrijvingk");
        taskDTO.setDeadlineDate(LocalDate.of(2021,7,20));
        taskDTO.setDeadlineTime(LocalTime.of(5,19));
        taskDTO.setId(3);

        service.addTask(taskDTO);

        List<TaskDTO> tasks = service.getTasks();

        TaskDTO task = new TaskDTO();
        task.setDeadlineDate(LocalDate.of(2021,5,20));
        task.setDeadlineTime(LocalTime.of(5,35));
        task.setTitle("Titel");
        task.setDescription("Beschrijving");

        service.editTask(1,task);

        tasks = service.getTasks();

        assertNotNull(tasks);
        assertFalse(tasks.isEmpty());
        assertEquals(1, tasks.size());
        assertEquals(task.getFormatted(), tasks.get(0).getFormatted());
    }

    @Test
    public void testAddSubtask() {
        SubTaskDTO subTaskDTO = new SubTaskDTO();
        subTaskDTO.setId(2);
        subTaskDTO.setTitle("Titel");
        subTaskDTO.setDescription("Desc");

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("Titel");
        taskDTO.setDescription("Beschrijving");
        taskDTO.setDeadlineDate(LocalDate.of(2021,5,20));
        taskDTO.setDeadlineTime(LocalTime.of(5,19));
        taskDTO.setId(2);

        service.addSubTask(subTaskDTO);

        TaskDTO task = service.getTask(2);

        assertNotNull(task);
        assertEquals(1, task.getSubTasks().size());
        assertEquals(task.getSubTasks().get(0).getDescription(),subTaskDTO.getDescription());
    }

}
