package com.coreServices.Songs.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Songs")
@XmlAccessorType(XmlAccessType.FIELD)
public class SongsWrapper {

    @XmlElement(name = "song")
    private List<Song> songList =  new ArrayList<>();

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(final List<Song> songs) {
        this.songList = new ArrayList<>(songs);
    }
}
