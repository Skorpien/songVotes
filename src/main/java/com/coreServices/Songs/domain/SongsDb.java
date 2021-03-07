package com.coreServices.Songs.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@NoArgsConstructor
public class SongsDb {

    /**
     * my database, list of all songs.
     */
    private final List<Song> songList =  new ArrayList<>();

    /**
     * adds a song to the database
     * first sends to check if a song exists in the database.
     * @param song - song sent for save
     */
    public void addSong(final Song song) {
        if (isSongExist(song)) {
            updateSong(song);
        } else {
            songList.add(song);
        }
        songList.sort(Comparator.comparing(Song::getId));
    }

    /**
     * add votes to song that exist in database.
     * @param song - song that is in the database and need to get votes from it
     */
    private void updateSong(final Song song) {
        if (song.getVotes() != null) {
            songList.get(songList.lastIndexOf(song))
                    .setVotes(songList.get(songList.lastIndexOf(song)).getVotes()
                            + song.getVotes());
        }
    }

    /**
     * checks if the song exists in the database.
     * @param song - song to check
     * @return - true if song exist in the database
     */
    private boolean isSongExist(final Song song) {
        return songList.contains(song);
    }

    /**
     * retrieves a copy of the list of songs stored in the database.
     * @return - copy of songs stored in the database
     */
    public List<Song> getSongList() {
        return new ArrayList<>(songList);
    }
}
