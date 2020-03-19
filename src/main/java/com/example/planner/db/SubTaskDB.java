package com.example.planner.db;

import com.example.planner.model.SubTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubTaskDB extends JpaRepository<SubTask, Integer> {
}