package com.unideb.hu.gtfsapi.Services;

import com.unideb.hu.gtfsapi.config.MongoDBConfiguration;
import com.unideb.hu.gtfsapi.entities.ChildNodeEntity;
import com.unideb.hu.gtfsapi.entities.MotherNodeEntity;
import com.unideb.hu.gtfsapi.entities.NodeEntity;
import com.unideb.hu.gtfsapi.enums.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.bson.Document;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StopsService {

    @Autowired
    MongoDBConfiguration configuration;

    public ResponseEntity<List<NodeEntity>> findByName(String database, String name) {
        MongoTemplate template = configuration.mongoTemplate(database);

        String jsonQuery = "{" +
                "$or : [" +
                "{\"name\" : {$regex :\""+ name +"\", $options: 'i'}}," +
                "{\"short-name\" : {$regex :\""+ name +"\", $options: 'i'}}," +
                "]" +
                "}";

        BasicQuery query = new BasicQuery(jsonQuery);

        List<NodeEntity> nodes =  template.find(query, NodeEntity.class, Collections.NODES.getName());

        if(nodes.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT)
                .body(nodes);

    }

    public ResponseEntity<NodeEntity> findByGtfsId(String database, long id) {
        MongoTemplate template = configuration.mongoTemplate(database);

        String queryString = "{" +
                "\"gtfs-id\" : " + id +
                "}";
        BasicQuery query = new BasicQuery(queryString);
        List<Document> candidates = template.find(query, Document.class, Collections.NODES.getName());

        if(candidates.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Document document = candidates.get(0);
        NodeEntity node = new NodeEntity();

        if (document.get("children") != null) {
            MotherNodeEntity motherNode = new MotherNodeEntity();
            List<Object> childrenIds = document.getList("children", Object.class);
            List<Long> ids = new ArrayList<>();

            if (!childrenIds.isEmpty()) {
                Object firstId = childrenIds.get(0);
                if (firstId instanceof Integer) {
                    for (Object childId : childrenIds) {
                        ids.add(((Integer) childId).longValue());
                    }
                } else if (firstId instanceof Long) {
                    for (Object childId : childrenIds) {
                        ids.add((Long) childId);
                    }
                }

                motherNode.setChildren(ids);
                node = motherNode;
            }
        } else if (document.get("parental-node") != null) {
            ChildNodeEntity childNode = new ChildNodeEntity();
            if(document.get("parental-node") instanceof Long){
                childNode.setMotherNode(document.getLong("parental-node"));
            } else
            {
                long parent = (long) document.getInteger("parental-node");
                childNode.setMotherNode(parent);
            }
            node = childNode;
        } else {
            node = new NodeEntity();
        }

        // Set common fields
        node.setMongoId(document.getObjectId("_id"));
        if(document.get("gtfs-id") instanceof Long){
            node.setId(document.getLong("parental-node"));
        } else
        {
            long parent = (long) document.getInteger("gtfs-id");
            node.setId(parent);
        }
        node.setName(document.getString("name"));
        node.setShortName(document.getString("short-name"));
        node.setDesc(document.getString("description"));
        node.setLongitude(document.getDouble("longitude"));
        node.setLatitude(document.getDouble("latitude"));


        return ResponseEntity.status(HttpStatus.OK).body(node);
    }
}
