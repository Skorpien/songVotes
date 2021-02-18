package com.coreServices.Songs.domain;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class SongCheckerTest {

    @Test
    public void checkingTheSame() {
        Song song1 = new Song(1L, "title1", "author1", 1L);
        Song song2 = new Song(2L, "title2", "author2", 1L);
        Song song3 = new Song(3L, "title3", "author3", 1L);
        Song song4 = new Song(4L, "title1", "author1", 1L);

        List<Song> currentSongs = new ArrayList<>();
        List<Song> newSongs = new ArrayList<>();
        currentSongs.add(song1);
        currentSongs.add(song2);
       // currentSongs.add(song3);
       // currentSongs.add(song4);
        newSongs.add(song1);
        newSongs.add(song2);
        newSongs.add(song3);
        newSongs.add(song4);

        SongChecker checker = new SongChecker();
       // Map<Long, Song> finalSongsList = checker.updateSongs(newSongs, currentSongs);

/*
            System.out.println(finalSongsList.get(1L).getVotes());
        System.out.println(finalSongsList.get(1L).getId());
        System.out.println(finalSongsList.size());*/


    }
}