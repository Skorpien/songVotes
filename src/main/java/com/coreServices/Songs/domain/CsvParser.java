package com.coreServices.Songs.domain;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
public class CsvParser {

    private static final String OBJECT_LIST_SAMPLE = "./object-list-sample.csv";

    public List<Song> csvRead(String file) throws IOException {

        return new CsvToBeanBuilder<Song>(new FileReader(file))
                .withType(Song.class)
                .build()
                .parse();
    }

    public void csvWrite(List<Song> songs, String path) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

        Writer writer = Files.newBufferedWriter(Paths.get(path));

        StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer)
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .build();

        beanToCsv.write(songs);
        writer.close();

    }
}
