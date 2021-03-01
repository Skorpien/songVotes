package com.coreServices.Songs.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Songs")
@XmlAccessorType(XmlAccessType.FIELD)
public class SongsWrapper {

    @XmlElement(name = "song")
    private List<Song> songList =  new ArrayList<>();

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songs) {
        this.songList = songs;
        for(Song song: songs) {
            song.setGenreToNull();
        }
    }
}
