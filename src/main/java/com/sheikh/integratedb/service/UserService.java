package com.sheikh.integratedb.service;


import com.sheikh.integratedb.models.User;
import com.sheikh.integratedb.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService  {
    @Autowired
    UserRepository userRepository;
    PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();

    public ResponseEntity<?> updateUser(String userName,User user){
        User existUser = userRepository.findByUserName(userName);
        if(existUser != null){
           if(user.getUserName() != null && !user.getUserName().equals("")){
               existUser.setUserName(user.getUserName());
           }
           if(user.getPassword() != null && !user.getPassword().equals("")){
               existUser.setPassword(passwordEncoder.encode(user.getPassword()));
           }
           userRepository.save(existUser);
           return new ResponseEntity<>(existUser, HttpStatus.OK);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> addUser(User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
        return new ResponseEntity<>(true, HttpStatus.CREATED);
        }catch(Exception e){
            log.error("error in adduser is{}",e.getMessage());
            log.info("error in adduser is{}",e.getMessage());
            log.trace("error in adduser is{}",e.getMessage());
            log.debug("error in adduser is{}",e.getMessage());
            log.warn("error in adduser is{}",e.getMessage());
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> saveUser(User user){
        try{
        userRepository.save(user);
            return new ResponseEntity<>(true , HttpStatus.OK);
        }catch (Exception e){
            log.error("error in saving user{}",e.getMessage());
//            System.out.println("Error in saving user service " +e.getMessage());
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<?> deleteUser(String id){
        try{
            userRepository.deleteById(id);
            return  new ResponseEntity<>(true , HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error("error in deleting user{}",e.getMessage());
//            System.err.println("error while deleting user"+ e.getMessage());
            return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> deleteByUserName(String userName) {

        try{
            userRepository.deleteByUserName(userName);
            return  new ResponseEntity<>(true , HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error("error in delete by username{}",e.getMessage());
//            System.err.println("error while deleting user"+ e.getMessage());
            return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    public ResponseEntity<?> getAllUsers(){
        List<User> alluser = userRepository.findAll();
        return alluser.isEmpty() ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(alluser , HttpStatus.OK);
    }

}


//    public ResponseEntity<?> getByID(String  id){
//        Optional<User> user = userRepository.findById(id);
//        return user.isPresent() ?
//                new ResponseEntity<>(user.get() , HttpStatus.OK) :
//                new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
