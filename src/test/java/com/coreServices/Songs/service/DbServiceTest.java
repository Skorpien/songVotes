package com.coreServices.Songs.service;

import com.coreServices.Songs.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @InjectMocks
    private DbService service;
    @Mock
    private CsvParser csvParser;
    @Mock
    private XmlParser xmlParser;
    @Mock
    private SongsDb songsDb;

    @Test
    public void getAllSongsTest() {
        //Given
        List<Song> songs = new ArrayList<>();
        songs.add(new Song());
        songs.add(new Song());

        //When
        when(service.getAllSongs()).thenReturn(songs);

        //Then
        List<Song> testList = service.getAllSongs();

        Assert.assertEquals(songs.size(), testList.size());
        Assert.assertEquals(songs.get(0).getId(), testList.get(0).getId());
        Assert.assertEquals(songs.get(1).getId(), testList.get(1).getId());
    }

    @Test
    public void saveSong() {

    }

    @Test
    public void readCsvFile() {
    }

    @Test
    public void writeToCsvFile() {
    }

    @Test
    public void readXmlFile() {
    }

    @Test
    public void writeToXmlFile() {
    }

    @Test
    public void getTop3Test() {
        //Given
        List<Song> songs = new ArrayList<>();
        songs.add(new Song("song1", 3L));
        songs.add(new Song("song2", 5L));
        songs.add(new Song("song3", 6L));
        songs.add(new Song("song4", 8L));

        //When
        when(service.getTop3()).thenReturn(songs);

        //Then
        List<Song> testList = service.getTop3();

        assertEquals(3, testList.size());
        assertEquals("song4", testList.get(0).getTitle());

    }

    @Test
    public void getTop10Test() {
        //Given
        List<Song> songs = new ArrayList<>();
        songs.add(new Song("song1", 3L));
        songs.add(new Song("song2", 5L));
        songs.add(new Song("song3", 6L));
        songs.add(new Song("song4", 8L));
        songs.add(new Song("song5", 11L));
        songs.add(new Song("song6", 7L));
        songs.add(new Song("song7", 5L));
        songs.add(new Song("song8", 4L));
        songs.add(new Song("song9", 3L));
        songs.add(new Song("song10", 0L));
        songs.add(new Song("song11", 1L));
        songs.add(new Song("song12", 2L));

        //When
        when(service.getTop10()).thenReturn(songs);

        //Then
        List<Song> testList = service.getTop10();

        assertEquals(10, testList.size());
        assertEquals("song5", testList.get(0).getTitle());
    }

    @Test
    public void getByCategoryTest() {
        //Given
        List<Song> songs = new ArrayList<>();
        Song song1 = new Song();
        Song song2 = new Song();
        Song song3 = new Song();
        song1.setCategory(Category.BLUES);
        song2.setCategory(Category.COUNTRY);
        song3.setCategory(Category.BLUES);
        songs.add(song1);
        songs.add(song2);
        songs.add(song3);

        //When
        when(service.getByCategory(Category.BLUES)).thenReturn(songs);

        //Then
        List<Song> testList = service.getByCategory(Category.BLUES);
        assertEquals(2, testList.size());
    }
}