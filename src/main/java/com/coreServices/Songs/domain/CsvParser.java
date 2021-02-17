package com.coreServices.Songs.domain;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Component
public class CsvParser {

    public List<Song> csvRead(String file) throws IOException {

        return new CsvToBeanBuilder<Song>(new FileReader(file))
                .withType(Song.class)
                .build()
                .parse();
    }
}
