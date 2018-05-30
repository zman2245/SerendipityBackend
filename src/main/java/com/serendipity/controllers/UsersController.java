package com.serendipity.controllers;

import com.serendipity.entities.User;
import com.serendipity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {

    private UserRepository userRepository;

    @Autowired
    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @ResponseBody
    public User createUser(@RequestParam String name,
                           @RequestParam(required = false, defaultValue = "") String bio) {

        User newUser = new User();
        newUser.setName(name);
        newUser.setBio(bio);

        userRepository.save(newUser);

        return newUser;
    }
}
