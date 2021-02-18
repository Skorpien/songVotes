package com.coreServices.Songs.domain;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "songs")
@XmlRootElement(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CsvBindByName(column = "Title")
    private String title;

    @CsvBindByName(column = "Author")
    private String author;

    @CsvBindByName(column = "Album")
    private String album;

    @CsvBindByName(column = "Category")
    @Enumerated(EnumType.STRING)
    private Category category;

    @CsvBindByName(column = "Votes")
    private Long votes;


    public void addVote() {
        setVotes(getVotes()+1L);
    }

    public void clearVotes() {
        setVotes(0L);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(title, song.title) && Objects.equals(author, song.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getAlbum() {
        return album;
    }

    public Category getCategory() {
        return category;
    }

    public Long getVotes() {
        return votes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlElement(name = "title")
    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement(name = "author")
    public void setAuthor(String author) {
        this.author = author;
    }

    @XmlElement(name = "album")
    public void setAlbum(String album) {
        this.album = album;
    }

    @XmlElement(name = "category")
    public void setCategory(Category category) {
        this.category = category;
    }

    @XmlElement(name = "votes")
    public void setVotes(Long votes) {
        this.votes = votes;
    }
}
