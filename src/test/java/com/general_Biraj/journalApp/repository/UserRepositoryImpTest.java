package com.general_Biraj.journalApp.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("dev")
public class UserRepositoryImpTest {

    @Autowired
    private UserRepositoryImp userRepositoryImp;


    @Test
    public void testSaveUser(){
        Assertions.assertNotNull(userRepositoryImp.getUserForSA());

    }
}
