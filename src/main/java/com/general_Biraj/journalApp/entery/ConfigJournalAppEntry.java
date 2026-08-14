package com.general_Biraj.journalApp.entery;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "config_journal_app")
@Data// contain @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCod method automatically
// so u don't have to generate them
@NoArgsConstructor
public class ConfigJournalAppEntry {
    //

    @NonNull
    private String key;
    private String value;



}
