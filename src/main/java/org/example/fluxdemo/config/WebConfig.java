package org.example.fluxdemo.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
    @Bean
    public MongoClient mongoClient() {
        return new MongoClient("localhost", 27017);
    }
}
