package com.example.planner.db;

import com.example.planner.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskDB extends JpaRepository<Task, String> {
}
