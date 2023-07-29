package com.luxsoloari.riotapidemo.client;

import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;

public class OriannaClient {
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(OriannaClient.class);
    private String apiKey;
    private String region;

    public OriannaClient(String apiKey, String region) {
        this.apiKey = apiKey;
        this.region = region;
    }

    @PostConstruct
    public void init() {
        Orianna.setRiotAPIKey(this.apiKey);
        Orianna.setDefaultRegion(Region.valueOf(this.region));
        LOGGER.info("OriannaClient initialized with API key: " + this.apiKey + " and region: " + this.region);
    }

    public Summoner getSummonerByName(String summonerName) {
        return Orianna.summonerNamed(summonerName).get();
    }

    @PreDestroy
    public void destroy() {
        LOGGER.info("OriannaClient destroyed.");
    }
}
