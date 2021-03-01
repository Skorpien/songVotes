package com.coreServices.Songs.domain;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.*;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
public class CsvParser {

    public List<Song> csvRead(String file) throws IOException {

        return new CsvToBeanBuilder<Song>(new FileReader(file))
                .withType(Song.class)
                .build()
                .parse();
    }

    public void csvWrite(List<Song> songs, String path) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

        Writer writer = Files.newBufferedWriter(Paths.get(path));


        StatefulBeanToCsv<Song> beanToCsv = new StatefulBeanToCsvBuilder<Song>(writer)
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .build();

        beanToCsv.write(songs);
        writer.close();

    }
}
