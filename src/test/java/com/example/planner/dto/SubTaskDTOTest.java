package com.example.planner.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SubTaskDTO.class)
public class SubTaskDTOTest {

    @Test
    public void testSetSuperTask() {
        TaskDTO task = new TaskDTO();

        task.setTitle("Titel");

        SubTaskDTO subTask = new SubTaskDTO();

        subTask.setId(task.getId());

        task.addSubTask(subTask);

        subTask.setSuperTask(task);

        assertEquals("Titel",subTask.getSuperTask().getTitle());
    }

    @Test
    public void testSubTaskConstructor3Param() {
        TaskDTO task = new TaskDTO();

        task.setTitle("Titel");

        String titel = "subTask1: do laundry";
        String desc = "Fold twice, ironing needed, sort by colour.";

        SubTaskDTO subTaskDTO = new SubTaskDTO(titel, desc, task);

        assertEquals(titel, subTaskDTO.getTitle());
        assertEquals(desc, subTaskDTO.getDescription());
        assertEquals(task, subTaskDTO.getSuperTask());
    }

    @Test
    public void testSubTaskConstructor2Param() {
        String titel = "subTask1: do laundry";
        String desc = "Fold twice, ironing needed, sort by colour.";

        SubTaskDTO subTaskDTO = new SubTaskDTO(titel, desc);

        assertEquals(titel, subTaskDTO.getTitle());
        assertEquals(desc, subTaskDTO.getDescription());
    }
}
