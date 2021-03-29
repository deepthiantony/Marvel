package com.marvel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MarvelResponse {
    Data data;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MarvelResponse{");
        sb.append("data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
