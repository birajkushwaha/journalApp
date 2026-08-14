package com.general_Biraj.journalApp.service;

import com.general_Biraj.journalApp.entery.JournalEntry;
import com.general_Biraj.journalApp.entery.User;
import com.general_Biraj.journalApp.repository.JournalEntryRepositry;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class JournalEntryService {
    @Autowired
    private JournalEntryRepositry journalEntryRepositry;

    @Autowired
    private UserService userService;



    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try {

            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepositry.save(journalEntry);
            user.getJournalEntryList().add(saved);
            userService.saveUser(user);
        }catch (Exception e){
            log.error("Error",e);
            throw  new RuntimeException(e);
        }

    }
    public void saveEntry(JournalEntry journalEntry){
      journalEntryRepositry.save(journalEntry);
    }

    public List<JournalEntry> getall(){
        return journalEntryRepositry.findAll();
    }
    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepositry.findById(id);
    }


    @Transactional
    public boolean deleteById(ObjectId id, String userName){
        boolean removed;
        try{

        User user = userService.findByUserName(userName);
        removed = user.getJournalEntryList().removeIf(x -> x.getId().equals(id));
        if(removed) {
            userService.saveUser(user);
            journalEntryRepositry.deleteById(id);
        }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return removed;

    }

}




//flow = controller --> service --> Repository extend Monog0Repository