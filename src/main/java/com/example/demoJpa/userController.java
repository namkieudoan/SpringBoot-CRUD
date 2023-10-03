package com.example.demoJpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class userController {
    @Autowired
    userRepository userRepository;
    @GetMapping()
    public ResponseEntity getAllUser(){
        List<User> listUser = userRepository.findAll();
        return ResponseEntity.ok().body(listUser);
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable(name = "id") Integer id ){
        User result = userRepository.findById(id).get();
        return result;
    }
    @PostMapping()
    public ResponseEntity createUser(@RequestBody User user){
        userRepository.save(user);
        return ResponseEntity.ok().body(user);
    }
    @PatchMapping("/{id}")
    public ResponseEntity updateUserById(@PathVariable(name = "id") Integer id, @RequestBody User user){
        User findedUser = userRepository.findById(id).get();
        //update
        findedUser.setAge(user.getAge());
        findedUser.setName(user.getName());
        findedUser.setPhone(user.getPhone());
        findedUser.setPassword(user.getPassword());
        userRepository.save(findedUser);
        return ResponseEntity.ok().body(findedUser);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable(name = "id") Integer id){
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    //sort
    @GetMapping("/sort-name={field}")
    public ResponseEntity getUserWithSort(@PathVariable(name = "field") String field){
        List<User> allUsers = userRepository.findAll(Sort.by(Sort.Direction.ASC,field));
        return ResponseEntity.ok().body(allUsers);
    }

}
