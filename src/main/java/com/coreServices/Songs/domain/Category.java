package com.coreServices.Songs.domain;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum Category {
    ROCK, METAL, HIPHOP, BLUES, JAZZ, COUNTRY, SOUL, DANCE, OTHER;

    public static Category category(String token)
    {
        return Category.valueOf(token);
    }
    public static String token(Category c)
    {
        return c.name();
    }
}

