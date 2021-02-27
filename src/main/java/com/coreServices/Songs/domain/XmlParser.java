package com.coreServices.Songs.domain;

import org.springframework.stereotype.Component;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
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
                    song.setCategory(song.getCategory());
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
        try
        {
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(com.coreServices.Songs.domain.Song.class);
            
            //Create Marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            //Required formatting??
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //Store XML to File
            File file = new File(path);

            //Writes XML file to file-system
            jaxbMarshaller.marshal(songs, file);
        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }
    }
}
