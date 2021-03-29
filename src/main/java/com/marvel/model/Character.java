package com.marvel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Character {
    private long id;
    private String name;
    private String description;
    private Thumbnail thumbnail;

}
