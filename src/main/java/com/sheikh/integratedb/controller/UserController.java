package com.sheikh.integratedb.controller;

import com.sheikh.integratedb.models.User;
import com.sheikh.integratedb.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
   @Autowired
   UserService userService;
//   @GetMapping("getall")
//   public ResponseEntity<?> getAllUsers(){
//       return userService.getAllUsers();
//   }

//   @GetMapping("get/{id}")
//   public ResponseEntity<?> getUser(@PathVariable String id){
////      ObjectId objectId = new ObjectId(id);
//      return userService.getByID(id);
//   }

   @PutMapping("update")
    public ResponseEntity<?> updateUser(@RequestBody User user){
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String userName = auth.getName();
//      ObjectId objectId = new ObjectId(id);
       return userService.updateUser(userName, user);
   }

   @DeleteMapping("delete")
    public ResponseEntity<?> deletUser(){
//      ObjectId objectId = new ObjectId(id);
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String userName = auth.getName();
       return userService.deleteByUserName(userName);
   }
}
