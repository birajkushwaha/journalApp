package com.general_Biraj.journalApp.repository;

import com.general_Biraj.journalApp.entery.ConfigJournalAppEntry;
import com.general_Biraj.journalApp.entery.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepositry extends MongoRepository<ConfigJournalAppEntry, ObjectId> {

}
