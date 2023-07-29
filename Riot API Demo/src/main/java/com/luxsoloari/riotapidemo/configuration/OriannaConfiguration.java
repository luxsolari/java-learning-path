package com.luxsoloari.riotapidemo.configuration;

import com.luxsoloari.riotapidemo.client.OriannaClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OriannaConfiguration {
    @Value("${riot.api.key}")
    private String apiKey;
    @Value("${riot.api.region}")
    private String region;

    @Bean
    public OriannaClient oriannaClient() {
        return new OriannaClient(apiKey, region);
    }
}
