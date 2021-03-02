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

    Category(final String label) {
        this.label = label;
    }

    public static Category valueOfLabel(final String label) {
        for (Category e : values()) {
            if (e.label.equalsIgnoreCase(label)) {
                return e;
            }
        }
        return Category.OTHER;
    }

    public String getLabel() {
        return label;
    }
}
