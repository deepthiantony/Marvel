package com.marvel.service;

import com.marvel.exception.MarvelException;
import com.marvel.model.Character;
import com.marvel.model.Data;
import com.marvel.model.MarvelResponse;
import com.marvel.service.impl.MarvelServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MarvelServiceTest {

    MarvelServiceImpl marvelService = new MarvelServiceImpl();
    GatewayService gatewayService = Mockito.mock(GatewayService.class);

    @BeforeEach
    public void init() {
    marvelService.setGatewayService(gatewayService);
    }

    @Test
    public void testGetCharacterIds(){
        ReflectionTestUtils.setField(marvelService, "limit", 1);
        List<Character> results=new ArrayList<>();
        results.add(new Character(12345,"IronMan","Strongest",null));
        Data data=new Data(results);
        MarvelResponse response=new MarvelResponse(data);
        Mockito.when(gatewayService.getCharacters(0)).thenReturn(response);
        Mockito.when(gatewayService.getCharacters(1)).thenReturn(response);
        MarvelResponse emptyResponse = new MarvelResponse(new Data( new ArrayList<>()));
        Mockito.when(gatewayService.getCharacters(2)).thenReturn(emptyResponse);
        Assertions.assertEquals(2,marvelService.getCharacterIds().size());
        }


    @Test
    public void  testGetCharacterIdsForNullResponse(){
        MarvelResponse response=null;
        //Mockito.when(gatewayService.getCharacters(1)).thenReturn(response);
        Assertions.assertThrows(MarvelException.class, () ->
                marvelService.getCharacterIds());
    }

    @Test
    public void testGetCharacterIdsForNullData(){
        Data data=null;
        MarvelResponse response=new MarvelResponse(data);
        Assertions.assertThrows(MarvelException.class,()->
                marvelService.getCharacterIds());
    }

    @Test
    public void testGetCharacterIdsForNullResults(){
        List<Character> results=null;
        Data data=new Data(results);
        MarvelResponse response=new MarvelResponse(data);
        Assertions.assertThrows(MarvelException.class,()->
                marvelService.getCharacterIds());

    }

    @Test
    public void testInstanceOfGetCharacterDetailsById(){
        long characterid=1234;
        List<Character> results=new ArrayList<>();
        results.add(new Character(12345,"IronMan","Strongest",null));
        Data data=new Data(results);
        MarvelResponse response=new MarvelResponse(data);
        Mockito.when(gatewayService.getCharacterById(1234)).thenReturn(response);
        Assertions.assertTrue(marvelService.getCharacterDetailsById(characterid) instanceof Character);
    }

    @Test
    public void testGetCharacterDetailsById(){
        List<Character> results=new ArrayList<>();
        results.add(new Character(1234,"IronMan","Strongest",null));
        Data data=new Data(results);
        MarvelResponse response=new MarvelResponse(data);
        Mockito.when(gatewayService.getCharacterById(1234)).thenReturn(response);
        Assertions.assertEquals(1234,marvelService.getCharacterDetailsById(1234).getId());
    }

    @Test
    public void testGetCharacterDetailsByIdForNullResponse(){
        MarvelResponse response=null;
        long characterId=1234;
        Assertions.assertThrows(MarvelException.class, () ->
                marvelService.getCharacterDetailsById(characterId));
    }
    @Test
    public void testGetCharacterDetailsByIdForNullData(){
        Data data=null;
        long characterId=1234;
        MarvelResponse response=new MarvelResponse(data);
        Assertions.assertThrows(MarvelException.class,()->
                marvelService.getCharacterDetailsById(characterId));
    }

    @Test
    public void testGetCharacterDetailsByIdForNullResults(){
        long characterId=1234;
        List<Character> results=null;
        Data data=new Data(results);
        MarvelResponse response=new MarvelResponse(data);
        Assertions.assertThrows(MarvelException.class,()->
                marvelService.getCharacterDetailsById(characterId));
    }

    @Test
    public void testGetCharacterDetailsByIdForInvalidId(){
        long characterId=123456;
        Assertions.assertThrows(MarvelException.class,()->
                marvelService.getCharacterDetailsById(characterId));
    }


}
