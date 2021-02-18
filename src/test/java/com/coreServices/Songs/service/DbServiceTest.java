package com.coreServices.Songs.service;

import com.coreServices.Songs.domain.Song;
import com.coreServices.Songs.repository.SongRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @InjectMocks
    private DbService service;

    @Mock
    private SongRepository songRepository;

    @Test
    public void getAllSongs() {
        //Before
        List<Song> songs = new ArrayList<>();
      //  Song song1 = new Song(1L,"as", "ss", 1L);
       // Song song2 = new Song(2L,"qw", "qq", 2L);

       // songs.add(song1);
        //songs.add(song2);

        //When
        Mockito.when(songRepository.findAll()).thenReturn(songs);

        //Then
        List<Song> testSongs = service.getAllSongs();

        assertEquals(songs.size(), testSongs.size());

    }
}