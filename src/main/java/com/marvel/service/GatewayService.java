package com.marvel.service;


import com.marvel.model.MarvelResponse;

public interface  GatewayService {
    public MarvelResponse getCharacters(int offset);
    public MarvelResponse getCharacterById(long characterId);

}
