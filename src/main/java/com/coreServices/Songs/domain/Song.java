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
    private final Integer id;

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
    private Long votes = 0L;

    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(0);

    /**
     * generate default Song object.
     */
    public Song() {
        id = ID_GENERATOR.incrementAndGet();
    }

    /**
     * generate Song with title and votes (useful for tests).
     * @param title - song title
     * @param votes - value of votes
     */
    public Song(final String title, final Long votes) {
        id = ID_GENERATOR.incrementAndGet();
        this.title = title;
        this.votes = votes;
    }

    /**
     * generate Song with title and author (useful for tests).
     * @param title - song title
     * @param author - song author
     */
    public Song(final String title, final String author) {
        id = ID_GENERATOR.incrementAndGet();
        this.title = title;
        this.author = author;
    }

    /**
     * add votes to the song.
     */
    public void addVote() {
        setVotes(getVotes() + 1L);
    }

    /**
     * clear song votes.
     */
    public void clearVotes() {
        setVotes(0L);
    }

    /**
     * check is the object are the same.
     * @param o - object to check
     * @return - true if object are the same
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(title, song.title) && Objects.equals(author, song.author);
    }

    /**
     * method that make unique code.
     * @return - unique code of object
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }

    /**
     * gets the id.
     * @return - object id field
     */
    public int getId() {
        return id;
    }

    /**
     * gets the title.
     * @return - object title field
     */
    public String getTitle() {
        return title;
    }

    /**
     * gets the author.
     * @return - object author field
     */
    public String getAuthor() {
        return author;
    }

    /**
     * gets the album.
     * @return - object album field
     */
    public String getAlbum() {
        return album;
    }

    /**
     * gets the category.
     * @return - object category field
     */
    public Category getCategory() {
        return category;
    }

    /**
     * gets the genre (used to help with xml conversion).
     * @return - object genre field
     */
    public String getGenre() {
        return genre;
    }

    /**
     * gets the votes
     * @return - object number of votes
     */
    public Long getVotes() {
        return votes;
    }


    public void setTitle(final String title) {
        this.title = title;
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    public void setAlbum(final String album) {
        this.album = album;
    }

    public void setCategory(final Category category) {
        this.category = category;
        this.genre = category.getLabel();
    }

    public void setGenre(final String genre) {
        this.genre = genre;
        this.category = Category.valueOfLabel(genre);
    }

    public void setVotes(final Long votes) {
        this.votes = votes;
    }
}