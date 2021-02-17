package com.coreServices.Songs.controller;

import com.coreServices.Songs.domain.Song;
import com.coreServices.Songs.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SongController {

    @Autowired
    private DbService dbService;

    public List<Song> songList() {
        return dbService.getAllSongs();
    }

    public void saveSong(Song song) {
        dbService.saveSong(song);
    }


}
