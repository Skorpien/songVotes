package com.coreServices.Songs.service;

import com.coreServices.Songs.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
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
        SongsDb songs = new SongsDb();
        songs.addSong(new Song());

        //When
        when(songsDb.getSongList()).thenReturn(songs.getSongList());

        //Then
        List<Song> testList = service.getAllSongs();

        Assert.assertEquals(songs.getSongList().size(), testList.size());
        Assert.assertEquals(songs.getSongList().get(0).getId(), testList.get(0).getId());
    }

    @Test
    public void saveSong() {
        //Given
        Song song = new Song();

        //When
        doNothing().when(songsDb).addSong(song);

        //Then
        service.saveSong(song);
        verify(songsDb,times(1)).addSong(song);

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
        SongsDb songs = new SongsDb();
        songs.addSong(new Song("song1", 3L));
        songs.addSong(new Song("song2", 5L));
        songs.addSong(new Song("song3", 6L));
        songs.addSong(new Song("song4", 8L));

        //When
        when(songsDb.getSongList()).thenReturn(songs.getSongList());

        //Then
        List<Song> testList = service.getTop3();

        assertEquals(3, testList.size());
        assertEquals("song4", testList.get(0).getTitle());

    }

    @Test
    public void getTop10Test() {
        //Given
        SongsDb songs = new SongsDb();
        songs.addSong(new Song("song1", 3L));
        songs.addSong(new Song("song2", 5L));
        songs.addSong(new Song("song3", 6L));
        songs.addSong(new Song("song4", 8L));
        songs.addSong(new Song("song5", 11L));
        songs.addSong(new Song("song6", 7L));
        songs.addSong(new Song("song7", 5L));
        songs.addSong(new Song("song8", 4L));
        songs.addSong(new Song("song9", 3L));
        songs.addSong(new Song("song10", 0L));
        songs.addSong(new Song("song11", 1L));
        songs.addSong(new Song("song12", 2L));

        //When
        when(songsDb.getSongList()).thenReturn(songs.getSongList());

        //Then
        List<Song> testList = service.getTop10();

        assertEquals(10, testList.size());
        assertEquals("song5", testList.get(0).getTitle());
    }

    @Test
    public void getByCategoryTest() {
        //Given
        SongsDb songs = new SongsDb();
        Song song1 = new Song("title1", "author1");
        Song song2 = new Song("title2", "author3");
        Song song3 = new Song("title3", "author3");
        song1.setCategory(Category.BLUES);
        song2.setCategory(Category.COUNTRY);
        song3.setCategory(Category.BLUES);
        songs.addSong(song1);
        songs.addSong(song2);
        songs.addSong(song3);

        //When
        when(songsDb.getSongList()).thenReturn(songs.getSongList());

        //Then
        List<Song> testList = service.getByCategory(Category.BLUES);
        assertEquals(2, testList.size());
    }
}