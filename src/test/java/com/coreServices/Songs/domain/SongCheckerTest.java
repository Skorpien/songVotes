package com.coreServices.Songs.domain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SongCheckerTest {

    @Test
    public void checkingTheSame() {
        Song song1 = new Song(1L, "title1", "author1", 0L);
        Song song2 = new Song(2L, "title2", "author2", 0L);
        Song song3 = new Song(3L, "title3", "author3", 0L);
        Song song4 = new Song(4L, "title1", "author1", 0L);

        List<Song> currentSongs = new ArrayList<>();
        List<Song> newSongs = new ArrayList<>();
        currentSongs.add(song1);
        newSongs.add(song2);
        newSongs.add(song3);
        newSongs.add(song4);

        SongChecker checker = new SongChecker();
        List<Song> finalSongsList = checker.checkingTheSame(newSongs, currentSongs);

        for(Song song: finalSongsList) {
            System.out.println(song.getAuthor());
        }

    }
}