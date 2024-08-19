package com.unideb.hu.gtfsapi.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "nodes")
public class NodeEntity {
    @Id
    @Field("gtfs-id")
    Long id;

    @Field("name")
    String name;

    @Field("short-name")
    String shortName;

    @Field("description")
    String desc;

    @Field("latitude")
    double latitude;

    @Field("longitude")
    double longitude;
}
