package com.general_Biraj.journalApp.service;

import com.general_Biraj.journalApp.entery.SentimentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SentimentConsumerService {
    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "weekly-sentiment", groupId = "weekly-sentiment-group")
    public void consume(SentimentData sentimentData){
        sendEmail(sentimentData);
    }
    public void sendEmail(SentimentData sentimentData){
        emailService.sendMail(sentimentData.getEmail(),"sentiment for previous week",sentimentData.getSentiment());
    }
}
