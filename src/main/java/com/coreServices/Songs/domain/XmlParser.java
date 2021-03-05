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

    /**
     * read .xml file, parse to Song.class.
     * @param file - file with .xml extension
     * @return - list of songs parsed from .xml file
     */
    public List<Song> xmlRead(final File file) {
        List<Song> songs = new ArrayList<>();
        try {
            JAXBContext context = JAXBContext.newInstance(Song.class);
            XMLInputFactory factory = XMLInputFactory.newFactory();
            factory.setProperty("javax.xml.stream.isCoalescing", true);
            StreamSource xml = new StreamSource(file);
            XMLStreamReader reader = factory.createXMLStreamReader(xml);

            Unmarshaller unmarshaller = context.createUnmarshaller();
            int i = -2;
            while (reader.getEventType() != XMLStreamReader.END_DOCUMENT) {
                if (reader.isStartElement() && "song".equals(reader.getLocalName())) {
                    Song song = (Song) unmarshaller.unmarshal(reader);
                    if (checkParsedSongs(song, i)) {
                        song.setGenre(song.getGenre());
                        songs.add(song);
                    }
                }
                reader.next();
                i++;
            }
        } catch (JAXBException | XMLStreamException ex) {
            System.out.println("problem with file");
        }
        return songs;
    }

    /**
     * write to .xml file from list of Song.class objects.
     * @param songs - song list sent for writing
     * @param path - user-specified write location
     */
    public void xmlWrite(final List<Song> songs, final String path) {
        SongsWrapper listOfSongs = new SongsWrapper();
        listOfSongs.setSongList(songs);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(SongsWrapper.class);

            Marshaller marshaller = jaxbContext.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            File file = new File(path);

            marshaller.marshal(listOfSongs, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     * validation method used for xmlRead method.
     * @param song - object sent for validation
     * @param i - iteration, used to find out where the error is in the file
     * @return - true if the object has properly initialized fields
     */
    public boolean checkParsedSongs(final Song song, final int i) {
        if (song.getTitle() == null || song.getAuthor() == null
                || song.getAlbum() == null || song.getGenre() == null) {
            System.out.println("The song in position "
                    + i
                    + " has incorrect data");
            return false;
        }
        return true;
    }
}
