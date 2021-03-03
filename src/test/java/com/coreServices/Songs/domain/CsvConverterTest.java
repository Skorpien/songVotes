package com.coreServices.Songs.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class CsvConverterTest {

    @Test
    public void convertTest() {
        //Given
        CsvConverter converter = new CsvConverter();
        String metal = "metal";

        //When
        Category category = (Category) converter.convert(metal);

        //Then
        Category testCategory = Category.METAL;
        assertEquals(testCategory, category);
    }
}