package com.example.lab5usertasks.controllers;

import com.example.lab5usertasks.models.Task;
import com.example.lab5usertasks.repositories.TaskRepository;
import com.example.lab5usertasks.repositories.UserRepository;
import com.example.lab5usertasks.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        userService.register(username, password);
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/task_list")
    public String taskList(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        List<Task> tasks = taskRepository.findByUser(userRepository.findByUsername(username).orElseThrow());
        model.addAttribute("tasks", tasks);
        return "task_list";
    }
}
