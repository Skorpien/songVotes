package com.coreServices.Songs.domain;


public class SongsChecker {

    public boolean checkParsedSongs(Song song) {
            if (song.getTitle() == null || song.getAuthor() == null
                    || song.getAlbum() == null || song.getGenre() == null) {
                System.out.println("The song has incorrect data");
                return false;
            }
        return true;
    }
}
