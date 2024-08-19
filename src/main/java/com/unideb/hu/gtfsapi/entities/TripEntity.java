package com.unideb.hu.gtfsapi.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "trips")
public class TripEntity {

    @Id
    @Field("trip-id")
    Long id;

    @Field("route-id")
    Long routeId;

    @Field("service-id")
    String serviceId;

    @Field("opposite-direction")
    boolean oppositeDirection;

    @Field("trip-headsign")
    String headsign;

    @Field("stops-reached")
    List<Long> stops;
}
