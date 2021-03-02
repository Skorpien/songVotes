package com.coreServices.Songs.domain;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import javax.xml.bind.annotation.*;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@XmlRootElement(name = "song")
@XmlAccessorType(XmlAccessType.FIELD)
public class Song {

    @XmlTransient
    private Integer id;

    @XmlElement(name = "title")
    @CsvBindByName(column = "Title")
    private String title;

    @XmlElement(name = "author")
    @CsvBindByName(column = "Author")
    private String author;

    @XmlElement(name = "album")
    @CsvBindByName(column = "Album")
    private String album;

    @XmlTransient
    @CsvCustomBindByName(column = "Category", converter = CsvConverter.class)
    private Category category;

    @XmlElement(name = "category")
    private String genre;

    @XmlElement(name = "votes")
    @CsvBindByName(column = "Votes")
    private Long votes;

    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(0);

    public Song() {
        id = ID_GENERATOR.incrementAndGet();
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
        setVotes(getVotes() + 1L);
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

    public Category getCategory() {
        return category;
    }

    public String getGenre() {
        return genre;
    }

    public Long getVotes() {
        return votes;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setCategory(Category category) {
        this.category = category;
        this.genre = category.getLabel();
    }

    public void setGenre(String genre) {
        this.genre = genre;
        this.category = Category.valueOfLabel(genre);
    }

    public void setVotes(Long votes) {
        this.votes = votes;
    }
}