package com.example.planner.dto;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = CreateUserDTO.class)
public class CreateUserDTOTest {
    private CreateUserDTO user;

    @BeforeEach
    public void setUp() {
        this.user = new CreateUserDTO();
    }

    @Test
    public void testSetName() {
        user.setUsername("John Doe");
        assertEquals("John Doe",user.getUsername());
    }

    @Test
    public void testSetPass() {
        user.setPassword("Missing");
        assertEquals("Missing",user.getPassword());
    }
}
