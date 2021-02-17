package com.coreServices.Songs.domain;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
                    songs.add(song);
                }
                reader.next();
            }
        }catch (JAXBException | XMLStreamException ex) {
            ex.printStackTrace();
        }
        return songs;
    }
}
