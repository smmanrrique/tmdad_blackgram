package com.tmdad.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class Global {

    @Bean
    public Map<String, String> consumerTags(){
        Map<String, String> map = new HashMap<String, String>();
        return map;
    }
}
