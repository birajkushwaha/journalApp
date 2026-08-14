package com.general_Biraj.journalApp.service;

import com.general_Biraj.journalApp.Schedular.UserSchedular;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
public class UserSchedularTest {
    @Autowired
    private UserSchedular userSchedular;

    @Test
    public void fetchUserSendUserSaMail() {
        userSchedular.fetchUserAndSendSaMail();
    }
}
