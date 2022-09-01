package com.example.poctrain.Controller;

import com.example.poctrain.Model.User;
import com.example.poctrain.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/users/login")
    public ResponseEntity<?> login(@RequestBody String data){
        System.out.println(data);
        String[] strs = data.split("&");
        if(strs[0].isEmpty() || strs[1].isEmpty()){
            return ResponseEntity.noContent().build();
        }
        String usrname = strs[0].substring(strs[0].indexOf("=")+1);
        String pwd = strs[1].substring(strs[1].indexOf("=")+1);
        User user1 = new User(usrname,pwd);
        try {
            userService.login(usrname,pwd);
        }catch(Exception e){
            return new ResponseEntity<>("Login Failed, incorrect username or password", HttpStatus.BAD_REQUEST);
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user1.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/users")
    public List<User> retrieveUsers(){
        return userService.retrieveUsers();
    }

    @PostMapping("/users")
    public ResponseEntity<?> addUser(@RequestBody User user){
        User createdUser = userService.addUser(user);
        if (createdUser == null) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> retireveById(@PathVariable Long id){
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    @PatchMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user){
        if(user == null){
            return ResponseEntity.noContent().build();
        }
        User user1 = userService.updateById(id,user);
        if(user1 == null){
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(
                user1,
                HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        int rslt = userService.deleteById(id);
        if(rslt == 0){
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(rslt,HttpStatus.OK);
    }
}
