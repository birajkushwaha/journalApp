package com.general_Biraj.journalApp.service;

import com.general_Biraj.journalApp.entery.User;
import com.general_Biraj.journalApp.repository.UserRepositry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserRepositry userRepositry;

    @Autowired
    private UserService userService;

    @ParameterizedTest
//    @ValueSource(strings = { // using value sourcr
//            "biraj",
//            "ram",
//            "rahul"
//    })
    @ArgumentsSource(UserArugmentSourceProvider.class) // other way using arugument provder
//
    public void testSaveUser(User user){
        assertTrue(userService.saveNewUser(user)); // test if value is null or not

    }
}
