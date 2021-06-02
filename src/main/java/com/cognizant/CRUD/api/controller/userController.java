package com.cognizant.CRUD.api.controller;


import com.cognizant.CRUD.api.domain.user;
import com.cognizant.CRUD.api.repository.userRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class userController {
    public userController(userRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final userRepository userRepository;
    @GetMapping("/user")
    public Iterable<user> getUsers(){
        return this.userRepository.findAll();
    }

    @PostMapping("/users")
    public user createUser(user user){
     return this.userRepository.save(user);
    }

    @GetMapping("/user/{id}")
    public user findUserById(Long id){
      return this.userRepository.findByid(id);
    }

    @PatchMapping("/user/{id}")
    public user replaceUser(String field, String value){
        // ***
        user user2 = this.userRepository.findByid(id);
        if(user2 == null){
            // ** throw an exception & send bad request
            return (long)HttpStatus.BAD_REQUEST.value();
        }else{
            this.userRepository.save(id);
            return this.userRepository.save(user2);
        }

    }

    @DeleteMapping("/user/{id}")
    public Long deleteUserId(Long id){
        user user = this.userRepository.findByid(id);
        if(user == null){
            return (long)HttpStatus.BAD_REQUEST.value();
        }else{
            this.userRepository.deleteById(id);
            return this.userRepository.count();
        }

    }

    @PostMapping("/users/authenticate")
    public user postUserAuthentication(user user){
        return this.userRepository.save(user);
    }
}
