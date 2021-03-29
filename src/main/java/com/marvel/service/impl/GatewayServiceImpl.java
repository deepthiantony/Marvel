package com.marvel.service.impl;

import com.marvel.exception.MarvelException;
import com.marvel.model.MarvelResponse;
import com.marvel.service.GatewayService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("gatewayService")
@Getter
public class GatewayServiceImpl implements GatewayService {
    private static final Logger logger = LoggerFactory.getLogger(GatewayServiceImpl.class);
    @Autowired
    private RestTemplate restTemplate;

    @Value("${gateway.service.ts}")
    private long ts;

    @Value("${gateway.service.api_key}")
    private String apikey;

    @Value("${gateway.service.hash}")
    private String hash;

    @Value("${gateway.service.limit}")
    private int limit;

    @Value("${gateway.service.url}")
    private String url;


    public String addRequiredParametersToUrl(String url) {
        return url + "?ts=" + ts + "&apikey=" + apikey + "&hash=" + hash;
    }

    public MarvelResponse getCharacters(int offset) {
        String newUrl = addRequiredParametersToUrl(url) + "&offset=" + offset + "&limit=" + limit;
        try {
            MarvelResponse response = restTemplate.getForObject(newUrl, MarvelResponse.class);
            return response;
        } catch (Exception exception) {
            logger.error("Error occured while retrieving character ids", exception);
            throw new MarvelException("Response cannot be found");
        }

    }

    public MarvelResponse getCharacterById(long characterId) {
        String newUrl = addRequiredParametersToUrl(url + "/" + characterId);
        try {
            MarvelResponse response = restTemplate.getForObject(newUrl, MarvelResponse.class);
            return response;
        } catch (Exception exception) {
            logger.error("Error occured while retrieving character details", exception);
            throw new MarvelException("Response cannot be found");
        }
    }
}
