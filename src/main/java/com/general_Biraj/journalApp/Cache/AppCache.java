package com.general_Biraj.journalApp.Cache;

import com.general_Biraj.journalApp.entery.ConfigJournalAppEntry;
import com.general_Biraj.journalApp.repository.ConfigJournalAppRepositry;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {
    public enum keys{
        WEATHER_API;
    }
    public Map<String,String> appCache;
    
    @Autowired
    private ConfigJournalAppRepositry configJournalAppRepositry;

    @PostConstruct
    public void init(){
        appCache = new HashMap<>();
        List<ConfigJournalAppEntry> all = configJournalAppRepositry.findAll();
       for(ConfigJournalAppEntry configJournalAppEntry:all){
           appCache.put(configJournalAppEntry.getKey() , configJournalAppEntry.getValue());
       }
    }
}
