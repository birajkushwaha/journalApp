package com.general_Biraj.journalApp.controller;

import com.general_Biraj.journalApp.apiResponse.WeatherResponse;
import com.general_Biraj.journalApp.entery.User;
import com.general_Biraj.journalApp.repository.UserRepositry;
import com.general_Biraj.journalApp.service.UserService;
import com.general_Biraj.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepositry userRepositry;

    @Autowired
    private WeatherService weatherService;


    @PostMapping
    public void createUser(@RequestBody User myUser) {
        userService.saveUser(myUser);

    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepositry.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather("Mumbai");
        String greeting="";
        if(weatherResponse !=null){
            greeting =" , weather/Temp feels like  "
                    + weatherResponse.getCurrent().getFeelslike();
        }

        return new ResponseEntity<>("Hii " + authentication.getName() + greeting, HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<?> udateUser(@RequestBody User myUser) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userService.findByUserName(userName);

        userInDb.setUserName(myUser.getUserName());
        userInDb.setPassword(myUser.getPassword());
        userService.saveNewUser(userInDb);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


}
