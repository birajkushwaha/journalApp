package com.general_Biraj.journalApp.controller;

import com.general_Biraj.journalApp.Cache.AppCache;
import com.general_Biraj.journalApp.entery.User;
import com.general_Biraj.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private AppCache appCache;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUser() {
        List<User> allUser = userService.getall();
        if (allUser != null && !allUser.isEmpty()) {
            return new ResponseEntity<>(allUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/create-admin-user")
    public void createAdmin(@RequestBody User user){
        userService.saveAdmin(user);
    }

    @GetMapping("{clean-app-cache}")
    public void cleanAppCache(){
        appCache.init();
    }
}
