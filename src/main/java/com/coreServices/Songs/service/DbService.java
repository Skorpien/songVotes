package com.coreServices.Songs.service;

import com.coreServices.Songs.domain.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class DbService {

    @Autowired
    private CsvParser csvParser;

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

 /*   public Song getSongById(int id) {
        return songsDb.getSongById(id);
    }*/

    public void parserChecker(File file) {
        if (file != null) {
            if (file.getAbsolutePath().endsWith(".csv")) {
                try {
                    readCsvFile(file.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (file.getAbsolutePath().endsWith(".xml")) {
                try {
                    readXmlFile(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("something wrong");
            }
        }
    }

    public void readCsvFile(final String file) throws Exception {
        List<Song> newSongs = csvParser.csvRead(file);
        for (Song song : newSongs) {
            saveSong(song);
        }
    }

    public void writeToCsvFile(final List<Song> songs, final String path) throws CsvRequiredFieldEmptyException,
            IOException, CsvDataTypeMismatchException {
        csvParser.csvWrite(songs, path);
    }

    public void readXmlFile(final File file) {
        List<Song> newSongs = xmlParser.xmlRead(file);
        for (Song song : newSongs) {
            saveSong(song);
        }
    }

    public void writeToXmlFile(final List<Song> songs, final String path) {
        xmlParser.xmlWrite(songs, path);
    }

    public List<Song> getTop3() {
        List<Song> allSongs = getAllSongs();
        allSongs.sort(Comparator.comparing(Song::getVotes).reversed());
        List<Song> top3 = new ArrayList<>();
        if (allSongs.size() >= 3) {
            for (int i = 0; i < 3; i++) {
                top3.add(allSongs.get(i));
            }
        } else {
            top3.addAll(allSongs);
            System.out.println("There is not enough songs");
        }
        return top3;
    }

    public List<Song> getTop10() {
        List<Song> allSongs = getAllSongs();
        allSongs.sort(Comparator.comparing(Song::getVotes).reversed());
        List<Song> top10 = new ArrayList<>();
        if (allSongs.size() >= 10) {
            for (int i = 0; i < 10; i++) {
                top10.add(allSongs.get(i));
            }
        } else {
            top10.addAll(allSongs);
            System.out.println("There is not enough songs");
        }
        return top10;
    }

    public List<Song> getByCategory(Category category) {
        List<Song> allSongs = getAllSongs();
        List<Song> byCategory = new ArrayList<>();
        for (Song song : allSongs) {
            if (song.getCategory().equals(category)) {
                byCategory.add(song);
            }
        }
        return byCategory;
    }

}
