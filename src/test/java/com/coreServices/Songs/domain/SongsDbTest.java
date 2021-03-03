package com.coreServices.Songs.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SongsDbTest {

    @Test
    public void addSongTest() {
        //Given
        SongsDb songs = new SongsDb();
        Song song = new Song();

        //When
        songs.addSong(song);

        //Then
        assertEquals(1, songs.getSongList().size());
    }

    @Test
    public void addTheSameSongTest() {
        //Given
        SongsDb songs = new SongsDb();
        Song song = new Song("title", "author");
        songs.addSong(song);

        //When
        songs.addSong(song);

        //Then
        assertEquals(1, songs.getSongList().size());
    }

    @Test
    public void getSongList() {
        //Given
        SongsDb songs = new SongsDb();
        Song song = new Song("title", "author");
        songs.addSong(song);

        //When
        List<Song> testList = songs.getSongList();

        //Then
        assertEquals(1, testList.size());
    }

}