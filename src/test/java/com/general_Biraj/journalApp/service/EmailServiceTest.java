package com.general_Biraj.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
public class EmailServiceTest {
    @Autowired
    private EmailService emailService;

    @Test
    void testSendEmail(){
        emailService.sendMail("birajkushwaha41@gmail.com",
                "see what i m doing",
                "hello i m using spring boot(java code) to send this email not using  gmail"
        );
    }
}
