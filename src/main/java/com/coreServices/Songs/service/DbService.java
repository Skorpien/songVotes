package com.coreServices.Songs.service;

import com.coreServices.Songs.domain.Song;
import com.coreServices.Songs.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbService {

    private final SongRepository songRepository;

    @Autowired
    public DbService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    public Song saveSong(final Song song) {
        return songRepository.save(song);
    }
}
