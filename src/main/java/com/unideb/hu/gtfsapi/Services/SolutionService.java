package com.unideb.hu.gtfsapi.Services;

import com.unideb.hu.gtfsapi.config.MongoDBConfiguration;
import com.unideb.hu.gtfsapi.entities.SolutionEntity;
import com.unideb.hu.gtfsapi.enums.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolutionService {

    @Autowired
    MongoDBConfiguration configuration;

    public ResponseEntity<SolutionEntity> findByStops(String database, long fromStop, long toStop){
        MongoTemplate template = configuration.mongoTemplate(database);

        String jsonQuery = "{ $and: [" +
                "{ \"from-id\": " + fromStop + " }, " +
                "{ \"to-id\": " + toStop + " }" +
                "] }";

        BasicQuery query = new BasicQuery(jsonQuery);

        List<SolutionEntity> solution = template.find(query, SolutionEntity.class, Collections.SOLUTIONS.getName());

        if(solution.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(solution.get(0));

    }

    public ResponseEntity<List<SolutionEntity>> findByFromStop(String database, long fromStop){
        MongoTemplate template = configuration.mongoTemplate(database);

        String jsonQuery = "{" +
                "\"from-id\":" + fromStop +
                "}";

        BasicQuery query = new BasicQuery(jsonQuery);
        List<SolutionEntity> solutionEntities = template.find(query, SolutionEntity.class, Collections.SOLUTIONS.getName());

        if(solutionEntities.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(solutionEntities);
    }

    public ResponseEntity<List<SolutionEntity>> findByToStop(String database, long toStop) {
        MongoTemplate template = configuration.mongoTemplate(database);

        String jsonQuery = "{" +
                "\"to-id\":" + toStop +
                "}";

        BasicQuery query = new BasicQuery(jsonQuery);
        List<SolutionEntity> solutionEntities = template.find(query, SolutionEntity.class, Collections.SOLUTIONS.getName());

        if(solutionEntities.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(solutionEntities);
    }
}
