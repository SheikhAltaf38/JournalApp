package com.sheikh.integratedb.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("journal_enrty")
@Data
@NoArgsConstructor
public class JournalEntry {
    @Id
    private String id;
    private String title;
    private String content;
    @CreatedDate
    private LocalDateTime time;
}
