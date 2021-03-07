package com.coreServices.Songs.domain;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
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

    private String error = "";

    /**
     * read .csv file, parse to Song.class, check incorrect data in file.
     * @param file - file with .csv extension
     * @return - list of songs parsed from .csv file
     * @throws IOException - incorrect loading
     */
    public List<Song> csvRead(final String file) throws IOException {
        CsvToBean<Song> beans = new CsvToBeanBuilder<Song>(new FileReader(file))
                .withType(Song.class)
                .withThrowExceptions(false)
                .build();

        List<Song> songs = beans.parse();

        beans.getCapturedExceptions()
                .forEach(e -> error += "The song in line "
                        + e.getLineNumber()
                        + " has incorrect data\n");

        beans.getCapturedExceptions()
                .forEach(e ->
                        System.out.println("The song in line "
                                + e.getLineNumber()
                                + " has incorrect data"));
        return songs;
    }

    /**
     * write to .csv file from list of Song.class objects.
     * @param songs - song list sent for writing
     * @param path - user-specified write location
     * @throws IOException - file was not written correctly
     * @throws CsvDataTypeMismatchException - incorrect data in .csv file
     * @throws CsvRequiredFieldEmptyException - no data to write to the specified field
     */
    public void csvWrite(final List<Song> songs, final String path) throws IOException,
            CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

        Writer writer = Files.newBufferedWriter(Paths.get(path));

        StatefulBeanToCsv<Song> beanToCsv = new StatefulBeanToCsvBuilder<Song>(writer)
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .build();

        beanToCsv.write(songs);
        writer.close();

    }

    /**
     * gets saved errors sends from csvReader().
     * @return - incorrect data errors
     */
    public String getError() {
        String errorToSend = error;
        error = "";
        return errorToSend;
    }
}
