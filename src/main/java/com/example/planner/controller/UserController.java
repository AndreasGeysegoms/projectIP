package com.example.planner.controller;

import com.example.planner.dto.CreateUserDTO;
import com.example.planner.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserServiceImpl userService;

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
