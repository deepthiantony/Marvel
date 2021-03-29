package com.marvel.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.marvel.exception.MarvelException;
import com.marvel.model.Character;
import com.marvel.model.MarvelResponse;
import com.marvel.service.GatewayService;
import com.marvel.service.MarvelService;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("marvelService")
public class MarvelServiceImpl implements MarvelService {
    private final Logger LOGGER = LoggerFactory.getLogger(MarvelServiceImpl.class);
    @Value("${gateway.service.limit}")
    private int limit;

    @Autowired
    @Getter
    @Setter
    GatewayService gatewayService;

    @Override
    @Cacheable(value = "marvelCache")
    public List<Long> getCharacterIds() {
        // while data is empty or  data is less than limit ,retrieve data from the API using offset and limit
        //map json to object
        //get the id from object and add to list
        //Return list
        List<Long> characterIdList = new ArrayList<>();
        int offset = 0;
        boolean isLessThanLimit = false;
        LOGGER.info("Fetching the character Ids");
        while (!isLessThanLimit) {
            MarvelResponse response = gatewayService.getCharacters(offset);
            if (response != null && response.getData() != null && response.getData().getResults() != null) {
                if (response.getData().getResults().size() < limit) {
                    isLessThanLimit = true;
                }
                characterIdList.addAll(response.getData().getResults().stream().map(s -> s.getId()).collect(Collectors.toList()));
            } else {
                throw new MarvelException("Results cannt be found");
            }
            offset = offset + limit;
        }
         return characterIdList;
    }


    @Override
    public Character getCharacterDetailsById(long characterId) {
        //Retrieve response from marvel api by providing the charaterid
        //from response,get the result
        Character character = null;
        MarvelResponse response = gatewayService.getCharacterById(characterId);
        if (response != null && response.getData() != null && response.getData().getResults() != null && response.getData().getResults().size() != 0) {
            character = response.getData().getResults().get(0);
        } else {
            throw new MarvelException("Character id cannot be found");
        }
        return character;
    }
}
