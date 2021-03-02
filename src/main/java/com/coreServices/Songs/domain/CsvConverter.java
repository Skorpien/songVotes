package com.coreServices.Songs.domain;

import com.opencsv.bean.AbstractBeanField;

public class CsvConverter extends AbstractBeanField {

    @Override
    protected Object convert(final String value) {
        for (Category e : Category.values()) {
            if (e.getLabel().equalsIgnoreCase(value)) {
                return e;
            }
        }
        return Category.OTHER;
    }
}
