package com.example.planner.dto;

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

        assertEquals("Eerste taak: due 24 oktober 2020 at 08:00",taskDTO.getFormatted());
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
        //assertEquals("Eerste Taak", subTaskDTO.getSuperTask().getTitle());
    }
}
