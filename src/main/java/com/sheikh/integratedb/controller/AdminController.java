package com.sheikh.integratedb.controller;

import com.sheikh.integratedb.models.User;
import com.sheikh.integratedb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("all-users")
    public ResponseEntity<?> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<?> addAdmin(@RequestBody User admin){
        return null;
    }
}
