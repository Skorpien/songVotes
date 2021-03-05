package com.coreServices.Songs.domain;

import com.opencsv.bean.AbstractBeanField;

public class CsvConverter extends AbstractBeanField {

    /**
     * method used in favor of CsvParser to look up the appropriate enum.
     * @param value - String value at which the enum is searched for
     * @return - matching enum
     */
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
