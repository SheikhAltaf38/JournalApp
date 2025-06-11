package com.sheikh.integratedb.controller;

import com.sheikh.integratedb.models.User;
import com.sheikh.integratedb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    UserService userService;
    @PostMapping("add")
    public ResponseEntity<?> addUser(@RequestBody User user){
        return userService.addUser(user);
    }

}


//    @GetMapping("all")
//    public ResponseEntity<?> getAll(){
//        return userService.getAllUsers();
//    }
