package com.general_Biraj.journalApp.Schedular;

import com.general_Biraj.journalApp.Cache.AppCache;
import com.general_Biraj.journalApp.entery.JournalEntry;
import com.general_Biraj.journalApp.entery.SentimentData;
import com.general_Biraj.journalApp.entery.User;
import com.general_Biraj.journalApp.enums.Sentiment;
import com.general_Biraj.journalApp.repository.UserRepositoryImp;
import com.general_Biraj.journalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserSchedular {
    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImp userRepositoryImp;



    @Autowired
    private AppCache appCache;


    @Autowired
    KafkaTemplate<String, SentimentData> kafkaTemplate;

//    @Scheduled(cron = "0 1 8 ? * WED")
    public void fetchUserAndSendSaMail(){
        List<User> users = userRepositoryImp.getUserForSA();
        for(User user:users){
            List<JournalEntry> journalEntries = user.getJournalEntryList();
            if (journalEntries == null || journalEntries.isEmpty()) {
                continue;
            }
            List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
            if (sentiments.isEmpty()) {
                System.out.println("No journal entries found in the last 7 days for user: " + user.getUserName());
                continue;
            }
            Map<Sentiment,Integer> sentimentCount = new HashMap<>();
            for(Sentiment sentiment:sentiments) {
                if (sentiment != null) {
                    sentimentCount.put(sentiment, sentimentCount.getOrDefault(sentiment, 0) + 1);
                }
            }
                Sentiment mostFreqSentiment =null;
            int maxCount =0;
            System.out.println(sentimentCount);
            System.out.println(sentimentCount.size());
            for(Map.Entry<Sentiment,Integer> entry : sentimentCount.entrySet()){
                if(entry.getValue()>maxCount) {
                    maxCount = entry.getValue();
                    mostFreqSentiment = entry.getKey();
                }
            }
            if(mostFreqSentiment!=null){
                SentimentData sentimentData = SentimentData.builder().email(user.getEmail()).sentiment("Sentiment for last 7 days is : " + mostFreqSentiment).build();
                try {kafkaTemplate.send("weekly-sentiment", sentimentData.getEmail(),sentimentData);
                } catch (Exception e) {
                    emailService.sendMail(sentimentData.getEmail(),"sentiment for previous week",sentimentData.getSentiment());
                }

            }

        }
    }
    @Scheduled(cron = "0 */10 * * * *")
    public void cleanAppCache(){
        appCache.init();

    }
}
