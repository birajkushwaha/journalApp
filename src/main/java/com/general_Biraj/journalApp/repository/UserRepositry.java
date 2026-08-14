package com.general_Biraj.journalApp.repository;

import com.general_Biraj.journalApp.entery.JournalEntry;
import com.general_Biraj.journalApp.entery.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepositry extends MongoRepository<User, ObjectId> {
    User findByUserName(String username);
    void deleteByUserName(String username);
}
