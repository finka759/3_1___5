package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.enteties.User;

public interface UserDetailsServicee extends UserDetailsService {
    public User findByUsername(String username);
}
