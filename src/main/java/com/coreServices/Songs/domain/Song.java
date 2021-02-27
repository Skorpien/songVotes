package com.coreServices.Songs.domain;

import com.opencsv.bean.CsvBindByName;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@XmlRootElement(name = "song")
@XmlAccessorType(XmlAccessType.FIELD)
public class Song {

    private int id;

    @XmlElement(name = "title")
    @CsvBindByName(column = "Title")
    private String title;

    @XmlElement(name = "author")
    @CsvBindByName(column = "Author")
    private String author;

    @XmlElement(name = "album")
    @CsvBindByName(column = "Album")
    private String album;

    private Category genre;

    @XmlElement(name = "category")
    @CsvBindByName(column = "Category")
    private String category;

    @XmlElement(name = "votes")
    @CsvBindByName(column = "Votes")
    private Long votes;

    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(0);

    public Song() {
        id=ID_GENERATOR.incrementAndGet();
    }

    public Song(String title, String author, String album, String category, Long votes) {
        this.title = title;
        this.author = author;
        this.album = album;
        this.category = category;
        this.genre = Category.valueOfLabel(category);
        this.votes = votes;
    }

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

    public int getId() {
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


    public String getCategory() {
        return category;
    }

    public Category getGenre() {
        return genre;
    }


    public Long getVotes() {
        return votes;
    }

/*    public void setId(int id) {
        this.id = id;
    }*/


    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setCategory(String category) {
        this.category = category;
        this.genre = Category.valueOfLabel(category);
    }

    public void setGenre(Category genre) {
        this.genre = genre;
    }

    public void setVotes(Long votes) {
        this.votes = votes;
    }
}