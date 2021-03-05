package com.coreServices.Songs.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@NoArgsConstructor
public class SongsDb {

    private final List<Song> songList =  new ArrayList<>();

    public void addSong(final Song song) {
        if (isSongExist(song)) {
            updateSong(song);
        } else {
            songList.add(song);
        }
        songList.sort(Comparator.comparing(Song::getId));
    }

    private void updateSong(final Song song) {
        if (song.getVotes() != null) {
            songList.get(songList.lastIndexOf(song))
                    .setVotes(songList.get(songList.lastIndexOf(song)).getVotes()
                            + song.getVotes());
        }
    }

    private boolean isSongExist(final Song song) {
        return songList.contains(song);
    }

    public List<Song> getSongList() {
        return new ArrayList<>(songList);
    }
}
