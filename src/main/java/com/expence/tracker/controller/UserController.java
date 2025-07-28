package com.expence.tracker.controller;


import com.expence.tracker.dto.UserDto;
import com.expence.tracker.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        log.info("USerController : getAllUSers() :");
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.FOUND);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        log.info("USerController : getUserById() :"+ id);
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.FOUND);
    }

    @PostMapping("/user")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto){
        log.info("USerController : saveUser() :"+userDto.name());
        return new ResponseEntity<>(userService.saveUser(userDto), HttpStatus.CREATED);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserDto> updateUserById(@RequestBody UserDto userDto, @PathVariable Long id){
        log.info("USerController : updateUserById() :"+ id);
        return new ResponseEntity<>(userService.updateByUserId(userDto, id), HttpStatus.CREATED);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id){
        log.info("USerController : deleteUserById() :"+ id);
        return new ResponseEntity<>(userService.deleteUserById(id), HttpStatus.FOUND);
    }
}
