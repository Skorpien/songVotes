package com.coreServices.Songs.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SongChecker {

    private final List<Song> checkedSongs = new ArrayList<>();

    public List<Song> checkingTheSame(List<Song> newSongsList, List<Song> currentSongsList) {
        if (!currentSongsList.isEmpty()) {
            for (Song checkSong : newSongsList) {
                for (Song currentSong :
                        currentSongsList) {
                    if (!checkSong.equals(currentSong)) {
                        checkedSongs.add(checkSong);
                    }
                }
            }
            return checkedSongs;
        } else {
            return newSongsList;
        }

    }
}
