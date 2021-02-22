package com.coreServices.Songs.domain;


import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum Category {
    Rock, Metal, HIPHOP, Blues, Jazz, Country, Soul, Dance, Other, Alternative, RNB;

  /*  public static Category category(String token)
    {
        return Category.valueOf(token);
    }
    public static String token(Category c)
    {
        return c.name();
    }*/
}

