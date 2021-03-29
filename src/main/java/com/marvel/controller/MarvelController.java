package com.marvel.controller;

import com.marvel.model.Character;
import com.marvel.service.MarvelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/v1")
@Controller
public class MarvelController {

    @Autowired
    MarvelService marvelService;

    @GetMapping("/characters")
    public ResponseEntity<List<Long>> getCharactersId(){
        List<Long> charcterIdList=marvelService.getCharacterIds();
        return ResponseEntity.ok().body(charcterIdList);
    }

    @GetMapping("/characters/{characterId}")
    public ResponseEntity<Character> getCharacterDetails(@PathVariable("characterId") long characterId){
        Character characterDetails=marvelService.getCharacterDetailsById(characterId);
        return ResponseEntity.ok().body(characterDetails);
    }
}
