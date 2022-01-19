package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.enteties.User;
import ru.kata.spring.boot_security.demo.services.UserDetailsServicee;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {
    private final UserService userService;
    private final UserDetailsServicee userDetailsServicee;

    @Autowired
    public MainController(UserService userService, UserDetailsServicee userDetailsServicee) {
        this.userService = userService;
        this.userDetailsServicee = userDetailsServicee;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        List<User> allUsers = userService.getUsers();
        return allUsers;
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", id));
        }
        return user;
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }

    @PatchMapping("/users")
    public User updatePatchUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "user id- " + id + " was deleted";
    }

    @GetMapping("/user")
    public User showUser(Model model, Principal principal){
        User user = userDetailsServicee.findByUsername(principal.getName());
        user.setPassword("");
        model.addAttribute("user", user);
        return user;
    }


}
