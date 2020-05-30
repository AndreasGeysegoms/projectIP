package com.example.planner.dto;

import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TaskDTO.class)
public class TaskDTOTest {

    @Test
    public void testSetId() {
        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setId(5);

        assertEquals(5,taskDTO.getId());
    }

    @Test
    public void testGetFormatted() {
        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setDescription("Taak: Plooi was op.");
        taskDTO.setTitle("Eerste taak");
        taskDTO.setDeadlineTime(LocalTime.of(8,0));
        taskDTO.setDeadlineDate(LocalDate.of(2020,10,24));

        assertEquals("Eerste taak: due 24 October 2020 at 08:00",taskDTO.getFormatted());
    }

    @Test
    public void testAddSubTask() {
        TaskDTO taskDTO = new TaskDTO();
        SubTaskDTO subTaskDTO = new SubTaskDTO();
        subTaskDTO.setId(taskDTO.getId());
        taskDTO.setTitle("Eerste taak");

        assertNull(taskDTO.getSubTasks());
        assertNull(subTaskDTO.getSuperTask());

        taskDTO.addSubTask(subTaskDTO);

        assertEquals(1,taskDTO.getSubTasks().size());
    }

    @Test
    public void testConstructor3Param() {
        TaskDTO taskDTO = new TaskDTO("Eerste taak","Taak #1", LocalDate.of(2000,1,15));

        assertEquals("Taak #1",taskDTO.getTitle());
        assertEquals("Eerste taak",taskDTO.getDescription());
        assertEquals(LocalDate.of(2000,1,15),taskDTO.getDeadlineDate());
    }

    @Test
    public void testConstructor5Param() {
        TaskDTO taskDTO = new TaskDTO("Eerste taak", LocalDate.of(2000,1,15),LocalTime.of(23,59),5,"Taak #1");

        assertEquals("Taak #1",taskDTO.getTitle());
        assertEquals("Eerste taak",taskDTO.getDescription());
        assertEquals(LocalDate.of(2000,1,15),taskDTO.getDeadlineDate());
        assertEquals(LocalTime.of(23,59),taskDTO.getDeadlineTime());
        assertEquals(5,taskDTO.getId());
    }

}
