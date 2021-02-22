package com.coreServices.Songs.domain;

import com.opencsv.bean.CsvBindByName;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@XmlRootElement(name = "song")
public class Song {

    private int id;

    @CsvBindByName(column = "Title")
    private String title;

    @CsvBindByName(column = "Author")
    private String author;

    @CsvBindByName(column = "Album")
    private String album;

    private Category category;

    @CsvBindByName(column = "Category")
    private String genre;

    @CsvBindByName(column = "Votes")
    private Long votes;

    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(0);

    public Song() {
        id=ID_GENERATOR.incrementAndGet();
    }

    public Song(String title, String author, String album, String genre, Long votes) {
        this.title = title;
        this.author = author;
        this.album = album;
        this.genre = genre;
        this.category = Category.valueOfLabel(genre);
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

    public String getGenre() {
        return genre;
    }

    public Category getCategory() {
        return category;
    }

    public Long getVotes() {
        return votes;
    }

/*    public void setId(int id) {
        this.id = id;
    }*/

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
    public void setGenre(String genre) {
        this.genre = genre;
        this.category = Category.valueOfLabel(genre);
    }


    public void setCategory(Category category) {
        this.category = category;
    }

    @XmlElement(name = "votes")
    public void setVotes(Long votes) {
        this.votes = votes;
    }
}