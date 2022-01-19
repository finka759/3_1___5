package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.enteties.Role;
import ru.kata.spring.boot_security.demo.enteties.User;
import ru.kata.spring.boot_security.demo.services.UserDetailsServicee;
import ru.kata.spring.boot_security.demo.services.UserService;


import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final UserDetailsServicee userDetailsService;


    @Autowired
    public UserController(UserService userService, UserDetailsServicee userDetailsService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/admin")
    public String findAll(Model model, Principal principal){
        User user = userDetailsService.findByUsername(principal.getName());
        user.setPassword("");
        model.addAttribute("user", user);
        User new_user = new User();
        model.addAttribute("new_user", new_user);
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        List<Role> listRoles = userService.listRoles();
        model.addAttribute("listRoles", listRoles);
        return "users";
    }

    @GetMapping("/admin/user_create")
    public String createUserForm(User user, Model model){
        List<Role> listRoles = userService.listRoles();
        model.addAttribute("listRoles", listRoles);
        return "user_create";
    }


    @PostMapping("/admin/user_create")
    public String createUser(User new_user){
        userService.saveUser(new_user);
        return "redirect:/admin ";
    }

    @GetMapping ("/admin/user_delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "redirect:/admin ";
    }

    @GetMapping("/admin/user_update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        User user = userService.getUserById(id);
        user.setPassword("");
        List<Role> listRoles = userService.listRoles();
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        return "user_update";
    }
    @PostMapping("/admin/user_update")
    public String updateUser(User user){
        userService.saveUser(user);
        return "redirect:/admin ";
    }

    @GetMapping("/user")
    public String showUser(Model model, Principal principal){
        User user = userDetailsService.findByUsername(principal.getName());
        user.setPassword("");
        model.addAttribute("user", user);
        return "user";
    }

}
