package com.coreServices.Songs.service;

import com.coreServices.Songs.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class DbService {

    @Autowired
    private CsvParser csvParserParser;

    @Autowired
    private XmlParser xmlParser;

    @Autowired
    private SongsDb songsDb;


    public List<Song> getAllSongs() {
        return songsDb.getSongList();
    }

    public void saveSong(final Song song) {
        songsDb.addSong(song);
    }

    public Song getSongById(int id) {
        return songsDb.getSongById(id);
    }

    public void readCsvFile(String file) throws Exception {
        List<Song> newSongs = csvParserParser.csvRead(file);
        for (Song song : newSongs) {
            saveSong(song);
        }
    }

    public void readXmlFile(File file) {
        List<Song> newSongs = xmlParser.xmlRead(file);
        for (Song song : newSongs) {
            saveSong(song);
        }
    }

    public List<Song> getTop3() {
        List<Song> allSongs = getAllSongs();
        allSongs.sort(Comparator.comparing(Song::getVotes).reversed());
        List<Song> top3 = new ArrayList<>();
        if(allSongs.size()>=3) {
            for (int i = 0; i < 3; i++) {
                top3.add(allSongs.get(i));
            }
        }else {
            top3.addAll(allSongs);
            System.out.println("There is not enough songs");
        }
        return top3;
    }

    public List<Song> getTop10() {
        List<Song> allSongs = getAllSongs();
        allSongs.sort(Comparator.comparing(Song::getVotes).reversed());
        List<Song> top10 = new ArrayList<>();
        if(allSongs.size()>=10) {
            for (int i = 0; i < 10; i++) {
                top10.add(allSongs.get(i));
            }
        }else {
            top10.addAll(allSongs);
            System.out.println("There is not enough songs");
        }
        return top10;
    }

}
