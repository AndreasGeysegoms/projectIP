package com.example.planner.controller;

import com.example.planner.db.UserRepository;
import com.example.planner.dto.CreateUserDTO;
import com.example.planner.dto.SubTaskDTO;
import com.example.planner.dto.TaskDTO;
import com.example.planner.model.Task;
import com.example.planner.model.User;
import com.example.planner.service.TaskService;
import com.example.planner.service.UserService;
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
        model.addAttribute("tasks", service.getTasks());
        return "tasks.html";
    }

    @RequestMapping("/tasks/{id}")
    @GetMapping
    public String showTask(@PathVariable("id") int id, Model model) {
        model.addAttribute("task", service.getTask(id));
        return "task.html";
    }

    @RequestMapping("/tasks/new")
    @GetMapping
    public String addTask() {
        return "newForm.html";
    }

    @RequestMapping("/tasks/new/submit")
    @PostMapping
    public String addTaskSubmit(@ModelAttribute TaskDTO task, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        service.addTask(task);
        return "redirect:/tasks";
    }

    @RequestMapping("/tasks/edit/{id}")
    public String editTask(@PathVariable("id") int id, Model model) {
        model.addAttribute("task", service.getTask(id));
        return "editTask.html";
    }

    @RequestMapping("/tasks/edit/submitChange")
    @PostMapping
    public String editTaskSubmit(@ModelAttribute TaskDTO task, Model model) {
        service.editTask(task.getId(), task);
        return "redirect:/tasks";
    }

    @RequestMapping("/tasks/subtask/submit")
    @PostMapping
    public String createSubTaskSubmit(@ModelAttribute SubTaskDTO subTask, Model model) {
        int idSuperTask = subTask.getId();
        service.addSubTask(subTask);
        model.addAttribute("task", service.getTask(idSuperTask));
        return "redirect:/tasks/" + idSuperTask;
    }

    @RequestMapping("/tasks/{id}/sub/create")
    public String createSubTask(@PathVariable String id, Model model) {
        model.addAttribute("id", id);
        return "newSubTask.html";
    }

    @RequestMapping("/")
    public String index() {
        return "navigation.html";
    }

    @GetMapping
    // Deze moet /login zijn; net zoals de post
    @RequestMapping("/login")
    public String fetchLogin() {
        return "login.html";
    }

    @RequestMapping("/registerPage")
    public String getRegisterPage(Model model){
        model.addAttribute("user",new CreateUserDTO());
        return "register.html";
    }

    @PostMapping
    @RequestMapping("/signup")
    public String postCreateUser(@ModelAttribute("user") @Valid CreateUserDTO user, BindingResult bindingResult) {
            if (bindingResult.hasErrors()) {
            return "redirect:/registerPage";
        }
        userService.createUser(user);
        System.out.println("Created user");
        System.out.println(user.toString());
        System.out.println(userService.loadUserByUsername(user.getUsername()).toString());
        return "redirect:/login";
    }


}