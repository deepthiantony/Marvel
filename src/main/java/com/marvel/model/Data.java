package com.marvel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Data {
    List<Character> results;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Data{");
        sb.append("characterDetailsList=").append(results);
        sb.append('}');
        return sb.toString();
    }
}
