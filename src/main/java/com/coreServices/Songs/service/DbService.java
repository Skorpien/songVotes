package com.coreServices.Songs.service;

import com.coreServices.Songs.domain.CsvParser;
import com.coreServices.Songs.domain.Song;
import com.coreServices.Songs.domain.SongChecker;
import com.coreServices.Songs.domain.XmlParser;
import com.coreServices.Songs.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class DbService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private CsvParser csvParserParser;

    @Autowired
    private XmlParser xmlParser;

    @Autowired
    private SongChecker checker;


    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    public void saveSong(final Song song) {
        songRepository.save(song);
    }

    public Song getSongById(Long id) throws Exception {
        return songRepository.findById(id).orElseThrow(Exception::new);
    }

    public void csvReader(String file) throws IOException {
        List<Song> songs = checker.checkingTheSame(csvParserParser.csvRead(file), getAllSongs());
        if (!songs.isEmpty()) {
            for (Song song : songs) {
                saveSong(song);
            }
        }
    }

    public void xmlReader(File file) throws Exception {
        List<Song> newSongs = xmlParser.xmlRead(file);
        List<Song> songs = checker.checkingTheSame(newSongs, getAllSongs());
        if (!songs.isEmpty()) {
            for (Song song : songs) {
                saveSong(song);
            }
        }
        checkSongs(newSongs, songs);
        newSongs.clear();
    }

    public void checkSongs(List<Song> newSongs, List<Song> addedSongs) throws Exception {
        Map<Long, Song> songsToUpdate = checker.updateSongs(newSongs, addedSongs, getAllSongs());
        if(!songsToUpdate.isEmpty()) {
            for (Map.Entry<Long, Song> entry : songsToUpdate.entrySet()) {
                Song song = getSongById(entry.getKey());
                song.setVotes(song.getVotes() + entry.getValue().getVotes());
                saveSong(song);
            }
        }
    }
}
