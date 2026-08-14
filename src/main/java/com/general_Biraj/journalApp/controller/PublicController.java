package com.general_Biraj.journalApp.controller;

import com.general_Biraj.journalApp.entery.User;
import com.general_Biraj.journalApp.service.UserDetailServiceImp;
import com.general_Biraj.journalApp.service.UserService;
import com.general_Biraj.journalApp.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailServiceImp userDetailServiceImp;
    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public void healthCheck(){
        System.out.println("healthy");
    }


    @PostMapping("/signup")
    public void createUser(@RequestBody User myUser){
        userService.saveNewUser(myUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User myUser){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(myUser.getUserName(), myUser.getPassword()));
            UserDetails userDetails = userDetailServiceImp.loadUserByUsername(myUser.getUserName());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);

        }catch (Exception e){
            log.error("Exception occur while create AuthenticationToken",e);
            return new ResponseEntity<>("incorrect username or password", HttpStatus.BAD_REQUEST);
        }
    }
}
