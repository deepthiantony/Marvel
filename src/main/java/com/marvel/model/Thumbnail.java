package com.marvel.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Thumbnail {
    private String path;
    private String extension;
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Thumbnail{");
        sb.append("path='").append(path).append('\'');
        sb.append(", extension='").append(extension).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
