package com.coreServices.Songs.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SongChecker {

    public List<Song> checkingTheSame(List<Song> newSongsList, List<Song> currentSongsList) {
        List<Song> addedList = new ArrayList<>(newSongsList);
        if(!currentSongsList.isEmpty()) {
            addedList.removeAll(currentSongsList);
        }
        return addedList;
    }

    public Map<Long,Song> updateSongs(List<Song> newSongsList, List<Song> addedSongs, List<Song> currentSongsList) {
        Map<Long, Song> songMap = new HashMap<>();
        newSongsList.removeAll(addedSongs);
        if(!currentSongsList.isEmpty()) {
            for (Song value : currentSongsList) {
                for (Song song : newSongsList) {
                    if (value.equals(song)) {
                        songMap.put(value.getId(), song);
                    }
                }
            }
        }
            return songMap;

    }
}
