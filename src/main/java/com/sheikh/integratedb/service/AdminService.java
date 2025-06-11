package com.sheikh.integratedb.service;

import com.sheikh.integratedb.models.User;
import com.sheikh.integratedb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AdminService {
    @Autowired
    private UserRepository userRepository;
 public ResponseEntity<?> addAdmin(User admin){
     try{
         admin.setRoles(Arrays.asList("ADMIN"));
         userRepository.save(admin);
         return new ResponseEntity<>(true, HttpStatus.OK);
     } catch (Exception e) {
         System.out.println("error in admin service" + e.getMessage());
         return new ResponseEntity<>(false , HttpStatus.INTERNAL_SERVER_ERROR);
     }
 }
}
