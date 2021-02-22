package com.coreServices.Songs.domain;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
@NoArgsConstructor
public class SongsDb {
    private final List<Song> songList =  new ArrayList<>();

    public void addSong(Song song) {
        if(isSongExist(song)) {
            updateSong(song);
        } else {
            songList.add(song);
        }
        songList.sort(Comparator.comparing(Song::getId));
    }

    private void updateSong(Song song) {
        songList.get(songList.lastIndexOf(song))
                .setVotes(songList.get(songList.lastIndexOf(song)).getVotes() + song.getVotes());
    }

    private boolean isSongExist(Song song) {
        return songList.contains(song);
    }

    public List<Song> getSongList() {
        return new ArrayList<>(songList);
    }

    public Song getSongById(int id) {
        for(Song song: songList) {
            if(song.getId()==id) {
                return song;
            }
        }
        return new Song();
    }
}
