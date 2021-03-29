package com.marvel.service;

import com.marvel.model.Character;

import java.util.List;


public interface MarvelService {
     public List<Long> getCharacterIds();
     public Character getCharacterDetailsById(long characterId);

}

