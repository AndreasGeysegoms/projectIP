package com.example.planner.dto;


import com.example.planner.model.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = UserDTO.class)
public class UserDTOTest {
    private UserDTO user;

    @BeforeEach
    public void setUp() {
        this.user = new UserDTO();
    }

    @Test
    public void testSetName() {
        user.setUsername("John Doe");
        assertEquals("John Doe",user.getUsername());
    }

    @Test
    public void testSetId() {
        user.setId((long)1986);
        assertEquals((long)1986,user.getId());
    }

    @Test
    public void testSetRole() {
        user.setRole(UserRole.ADMIN);
        assertEquals(UserRole.ADMIN,user.getRole());
    }
}
