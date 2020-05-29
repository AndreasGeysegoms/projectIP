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
public class TaskController {

    @Autowired
    private TaskService service;
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/tasks")
    @GetMapping
    public String showTasks(Model model) {
        // taken meegeven aan de model
        model.addAttribute("tasks", service.getTasks());
        return "tasks.html";
    }

    @RequestMapping("/tasks/{id}")
    @GetMapping
    public String showTask(@PathVariable("id") int id, Model model) {
        try {
            model.addAttribute("task", this.service.getTask(id));
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "task.html";
    }

    @GetMapping("/tasks/new")
    public String addTask(Model model) {
        // geef een lege taak mee
        model.addAttribute("task",new TaskDTO());
        return "newForm.html";
    }

    @PostMapping("/tasks/new")
    public String addTaskSubmit(@ModelAttribute @Valid TaskDTO task , Model model, BindingResult bindingResult) {
        // bindingResult checkt of er iets fout is
        if (bindingResult.hasErrors()) {
            return "newForm";
        }
        service.addTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/edit/{id}")
    public String editTask(@PathVariable("id") int id, Model model) {
        try {
            model.addAttribute("task", this.service.getTask(id));
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        // taak meegeven aan de model
        return "editTask.html";
    }

    @PostMapping("/tasks/edit/{id}")
    public String editTaskSubmit(@ModelAttribute("task") @Valid TaskDTO task, Model model, @PathVariable("id") int id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editTask";
        }
        service.editTask(task.getId(), task);
        return "redirect:/task/"+task.getId();
    }

    @PostMapping("/tasks/{id}/sub/create")
    // @Valid en BindingResult nodig om te checken
    public String createSubTaskSubmit(@ModelAttribute("subTask") @Valid SubTaskDTO subTask, Model model, BindingResult bindingResult, @PathVariable String id) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("id",id); // anders spelen we deze kwijt
            return "newSubTask";
        }
        int idSuperTask = subTask.getId();
        service.addSubTask(subTask);
        model.addAttribute("task", service.getTask(idSuperTask));
        return "redirect:/tasks/" + idSuperTask;
    }

    @GetMapping("/tasks/{id}/sub/create")
    public String createSubTask(@PathVariable String id, Model model) {
        model.addAttribute("id", id);
        // geef een lege taak mee
        model.addAttribute("subTask",new SubTaskDTO());
        return "newSubTask.html";
    }

    @RequestMapping("/")
    public String index() {
        return "navigation.html";
    }

    // Deze moet /login zijn; net zoals de post
    @GetMapping("/login")
    public String fetchLogin() {
        // geen user klaarzetten omdat deze geen nieuwe persoon in db pusht
        return "login.html";
    }

    @GetMapping("/signup")
    public String getRegisterPage(Model model){
        // deze lege user klaarzetten om door te geven aan de form
        model.addAttribute("user",new CreateUserDTO());
        return "register.html";
    }

    @PostMapping("/signup")
    public String postCreateUser(@ModelAttribute("user") @Valid CreateUserDTO user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        userService.createUser(user);
        return "redirect:/login";
    }
}