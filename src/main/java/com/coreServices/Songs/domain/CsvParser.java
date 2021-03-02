package com.coreServices.Songs.domain;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
public class CsvParser {

    public List<Song> csvRead(final String file) throws IOException {

        return new CsvToBeanBuilder<Song>(new FileReader(file))
                .withType(Song.class)
                .build()
                .parse();
    }

    public void csvWrite(final List<Song> songs, final String path) throws IOException,
            CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

        Writer writer = Files.newBufferedWriter(Paths.get(path));


        StatefulBeanToCsv<Song> beanToCsv = new StatefulBeanToCsvBuilder<Song>(writer)
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .build();

        beanToCsv.write(songs);
        writer.close();

    }
}
