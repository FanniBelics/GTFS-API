package com.unideb.hu.gtfsapi.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.UUID;

@Data
@Document(collection =  "routes")
public class RouteEntity {
    @Id
    @Field("route-id")
    UUID routeId;

    @Field("agency-id")
    String agencyID;

    @Field("route-description")
    String desc;

    @Field("stops-reached")
    List<Long> stopsReached;

    @Field("route-short-name")
    String shortName;

    @Field("route-long-name")
    String longName;

    @Field("route-type-as-text")
    String type;
}
