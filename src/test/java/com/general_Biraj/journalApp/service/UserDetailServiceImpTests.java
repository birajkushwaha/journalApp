package com.general_Biraj.journalApp.service;

import com.general_Biraj.journalApp.entery.User;
import com.general_Biraj.journalApp.repository.UserRepositry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@ActiveProfiles("dev")
public class UserDetailServiceImpTests {
    @InjectMocks
    private UserDetailServiceImp userDetailServiceImp;

    @Mock
    private UserRepositry userRepositry;

    @BeforeEach
    void  setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsernameTest() {
        when(userRepositry.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("biraj").password("afafafafsa").role(new ArrayList<>()).build());
        UserDetails user = userDetailServiceImp.loadUserByUsername("biraj");
        Assertions.assertNotNull(user);

    }

}
