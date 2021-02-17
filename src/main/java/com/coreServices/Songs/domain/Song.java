package com.coreServices.Songs.domain;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CsvBindByName(column = "Title")
    private String title;

    @CsvBindByName(column = "Author")
    private String author;

    @CsvBindByName(column = "Votes")
    private Long votes;

    public void addVote() {
        setVotes(getVotes()+1L);
    }

    public void clearVotes() {
        setVotes(0L);
    }
}
