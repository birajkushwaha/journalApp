package com.general_Biraj.journalApp.entery;

import com.general_Biraj.journalApp.enums.Sentiment;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries")
@Data// contain @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCod method automatically
// so u don't have to generate them
@NoArgsConstructor
public class JournalEntry {
    //
    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;
    private Sentiment sentiment;


}
