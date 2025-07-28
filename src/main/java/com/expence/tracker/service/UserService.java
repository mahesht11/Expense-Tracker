package com.expence.tracker.service;

import com.expence.tracker.dto.UserDto;
import com.expence.tracker.entity.User;
import com.expence.tracker.exception.UserNotFoundException;
import com.expence.tracker.repos.UserRepo;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Builder(toBuilder = true)
public class UserService {

    @Autowired
    private UserRepo userRepo;


    public List<UserDto> getAllUsers() {
        log.info("UserService class : getAllUsers() :");
        return userRepo.findAll().stream().map(user -> new UserDto(user.getId(), user.getName(), user.getEmail(), user.getAge())).collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        log.info("UserService class : getUserById() :"+id);
       User user  = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with this id : "+id));
       if(user.getId().equals(id)){
           return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getAge());
       }
       return null;
    }

    public UserDto saveUser(UserDto userDto) {
        log.info("UserService class : saveUser() :");
        User user = User.builder()
                .name(userDto.name())
                .email(userDto.email())
                .age(userDto.age())
                .build();
        User user1 = userRepo.saveAndFlush(user);
        return new UserDto(user1.getId(), user1.getName(), user1.getEmail(), user1.getAge());
    }

    public UserDto updateByUserId(UserDto userDto, Long id) {
        log.info("UserService class : updateByUserId() :"+ id);
        User user  = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with this id : "+id));
        if(!ObjectUtils.isEmpty(user)){
             user.setName(userDto.name()!=null?userDto.name():user.getName());
             user.setEmail(userDto.email()!=null?userDto.email():user.getEmail());
             user.setAge(userDto.age()!=null? userDto.age() : user.getAge());
            User user2 = userRepo.saveAndFlush(user);
            return new UserDto(user2.getId(), user2.getName(), user2.getEmail(), user2.getAge());
        }
        return null;
    }

    public String deleteUserById(Long id) {
        log.info("UserService class : deleteUserById() :"+id);
        User user  = userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with this id : "+id));
        if(user.getId().equals(id)){
            userRepo.deleteById(id);
            return "Successfully delete the user with this id :"+id;
        }
        return null;
    }
}
