package com.example.planner.controller;

import com.example.planner.dto.CreateUserDTO;
import com.example.planner.dto.SubTaskDTO;
import com.example.planner.dto.TaskDTO;
import com.example.planner.service.TaskService;
import com.example.planner.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping
    public String showTasks(Model model) {
        // taken meegeven aan de model
        model.addAttribute("tasks", service.getTasks());
        return "tasks.html";
    }

    @RequestMapping("/{id}")
    @GetMapping
    public String showTask(@PathVariable("id") int id, Model model) {
        try {
            model.addAttribute("task", this.service.getTask(id));
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "task.html";
    }

    @GetMapping("/new")
    public String addTask(Model model) {
        // geef een lege taak mee
        model.addAttribute("task", new TaskDTO());
        return "newForm.html";
    }

    @PostMapping("/new")
    // spring kiest standaard camelCase class bij ModelAttribute, Model niet gebruiken, want spring denkt dat je dit zelf uitwerkt
    public String addTaskSubmit(@ModelAttribute("task") @Valid TaskDTO task, BindingResult bindingResult) {
        // bindingResult checkt of er iets fout is
        if (bindingResult.hasErrors()) {
            return "newForm";
        }
        service.addTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String editTask(@PathVariable("id") int id, Model model) {

        // taak meegeven aan de model
        model.addAttribute("task", this.service.getTask(id));
        this.service.getTask(id);

        return "editTask.html";
    }

    @PostMapping("/edit/{id}")
    public String editTaskSubmit(@ModelAttribute("task") @Valid TaskDTO task, BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "editTask";
        }
        service.editTask(task.getId(), task);
        return "redirect:/tasks/" + id;
    }

    @PostMapping("/{id}/sub/create")
    // @Valid en BindingResult nodig om te checken
    public String createSubTaskSubmit(@ModelAttribute("subTask") @Valid SubTaskDTO subTask/*, Model model*/, BindingResult bindingResult, @PathVariable String id) {
        if (bindingResult.hasErrors()) {
            return "newSubTask";
        }
        service.addSubTask(subTask);
        return "redirect:/tasks/" + id;
    }

    @GetMapping("/{id}/sub/create")
    public String createSubTask(@PathVariable String id, Model model) {
        model.addAttribute("id", id);
        // geef een lege taak mee
        model.addAttribute("subTask", new SubTaskDTO());
        return "newSubTask.html";
    }
}