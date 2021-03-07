package com.coreServices.Songs.domain;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum Category {
    ROCK("Rock"),
    METAL("Metal"),
    HIPHOP("HipHop"),
    BLUES("Blues"),
    JAZZ("Jazz"),
    COUNTRY("Country"),
    SOUL("Soul"),
    DANCE("Dance"),
    ALTERNATIVE("Alternative"),
    RNB("R&B"),
    OTHER("Other");

    private final String label;

    /**
     * enums with values of Category field.
     * @param label - label to compare
     */
    Category(final String label) {
        this.label = label;
    }

    /**
     *
     * @param label - String value at which the enum is searched for
     * @return - matching enum
     */
    public static Category valueOfLabel(final String label) {
        for (Category e : values()) {
            if (e.label.equalsIgnoreCase(label)) {
                return e;
            }
        }
        return Category.OTHER;
    }

    /**
     * gets label field
     * @return - label field
     */
    public String getLabel() {
        return label;
    }
}
