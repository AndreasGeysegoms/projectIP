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
}
