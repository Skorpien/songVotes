package com.coreServices.Songs.domain;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.*;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import org.springframework.stereotype.Component;

@Component
public class XmlParser {

    private final List<Song> songs = new ArrayList<>();

    public List<Song> xmlRead(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(Song.class);
            XMLInputFactory factory = XMLInputFactory.newFactory();
            StreamSource xml = new StreamSource(file);
            XMLStreamReader reader = factory.createXMLStreamReader(xml);

            Unmarshaller unmarshaller = context.createUnmarshaller();
            while(reader.getEventType() != XMLStreamReader.END_DOCUMENT) {
                if (reader.isStartElement() && "song".equals(reader.getLocalName())) {
                    Song song = (Song) unmarshaller.unmarshal(reader);
                    song.setGenre(song.getGenre());
                    System.out.println("enum: " + song.getCategory());
                    System.out.println("string: " + song.getGenre());
                    songs.add(song);
                }
                reader.next();
            }
        }catch (JAXBException | XMLStreamException ex) {
            ex.printStackTrace();
        }
        return songs;
    }

    public void xmlWrite(List<Song> songs, String path)
    {
        SongsWrapper listOfSongs = new SongsWrapper();
        listOfSongs.setSongList(songs);
        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance(SongsWrapper.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            File file = new File(path);

            jaxbMarshaller.marshal(listOfSongs, file);
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
    }
}
