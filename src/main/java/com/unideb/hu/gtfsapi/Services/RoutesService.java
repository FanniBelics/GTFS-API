package com.unideb.hu.gtfsapi.Services;

import com.unideb.hu.gtfsapi.config.MongoDBConfiguration;
import com.unideb.hu.gtfsapi.entities.RouteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoutesService {

    @Autowired
    MongoDBConfiguration configuration;

    public ResponseEntity<RouteEntity> findById(String database, long id) {
        MongoTemplate template = configuration.mongoTemplate(database);

        String jsonQuery = "{" +
                "\"route-id\" : "+ id +
                "}";

        BasicQuery query = new BasicQuery(jsonQuery);

        List<RouteEntity> routeEntities = template.find(query, RouteEntity.class, database);

        if(routeEntities.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(routeEntities.get(0));
    }

    public ResponseEntity<List<RouteEntity>> findByType(String database, int type) {
        MongoTemplate template = configuration.mongoTemplate(database);

        String jsonQuery = "{" +
                "\"route-type\" : "+ type +
                "}";

        BasicQuery query = new BasicQuery(jsonQuery);

        List<RouteEntity> routeEntities = template.find(query, RouteEntity.class, database);

        if(routeEntities.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(routeEntities);
    }

    public ResponseEntity<List<RouteEntity>> findByName(String database, String name) {
        MongoTemplate template = configuration.mongoTemplate(database);

        String jsonQuery = "{" +
                "$or : [" +
                "{\"route-short-name\" : "+ name + "}, " +
                "{\"route-long-name\": " + name + "}]" +
                "}";

        BasicQuery query = new BasicQuery(jsonQuery);

        List<RouteEntity> routeEntities = template.find(query, RouteEntity.class, database);

        if(routeEntities.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(routeEntities);
    }

    public ResponseEntity<List<RouteEntity>> findIfContainsStop(String database, long stopId) {
        MongoTemplate template = configuration.mongoTemplate(database);

        String jsonQuery = "{" +
                "\"stops-reached\" : "+ stopId +
                "}";

        BasicQuery query = new BasicQuery(jsonQuery);

        List<RouteEntity> routeEntities = template.find(query, RouteEntity.class, database);

        if(routeEntities.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(routeEntities);
    }

    public ResponseEntity<Long> count(String database, String jsonQuery) {
        MongoTemplate template = configuration.mongoTemplate(database);

        if(jsonQuery.isEmpty()){
            jsonQuery = "{}";
        }
        BasicQuery query = new BasicQuery(jsonQuery);

        long count = template.count(query, database);

        return ResponseEntity.status(HttpStatus.OK).body(count);
    }
}
