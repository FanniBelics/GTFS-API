package com.unideb.hu.gtfsapi.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoDBConfiguration {

    Dotenv dotenv = Dotenv.load();
    String mongoUri = dotenv.get("MONGO_URI");

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(mongoUri);
    }

    public MongoTemplate mongoTemplate(String databaseName) {
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(mongoClient(), databaseName));
    }
}
