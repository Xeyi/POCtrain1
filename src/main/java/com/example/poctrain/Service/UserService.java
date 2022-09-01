package com.example.poctrain.Service;

import com.example.poctrain.Model.User;
import com.example.poctrain.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static List<User> users = new ArrayList<>();
    @Autowired
    UserRepository userRepository;

    public User login(String usrname, String pwd){
        List<User> users = userRepository.findAll();
        for(User u:users){
            if(u.getUsrname().equals(usrname)){
                if(u.getPwd().equals(pwd)){
                    return u;
                }
            }
        }
        throw new RuntimeException("Failed to login, username or password is incorrect");
    }

    public List<User> retrieveUsers(){
        return userRepository.findAll();
    }

    public User addUser(User user){
        if(user.getUsrname() == null || user.getPwd() == null){
            throw new RuntimeException("User cannot be null");
        }
        User user1 = userRepository.save(user);
        return user1;
    }

    public Optional<User> findById(Long id){
        Optional<User> theuser = userRepository.findById(id);
        if(theuser == null){
            throw new RuntimeException("Cannot find the user by id");
        }
        return theuser;
    }

    public User updateById(Long id, User newUser){
        if(userRepository.findById(id) == null){
            return null;
        }
        return userRepository.save(newUser);
    }

    public int deleteById(Long id){
        int result;
        if(userRepository.findById(id) == null) {
            result = 0;
            throw new RuntimeException("User not found");
        }
        else {
            userRepository.deleteById(id);
            result = 1;
        }
        return result;
    }

}
