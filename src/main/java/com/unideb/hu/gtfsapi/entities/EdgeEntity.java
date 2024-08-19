package com.unideb.hu.gtfsapi.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Data
@Document(collection = "edges")
public class EdgeEntity {

    @Id
    @Field("id")
    long id;

    @Field("from-stop")
    long fromStop;

    @Field("to-stop")
    long toStop;

    @Field("distance")
    int distance;

    @Field("travelling-time-mins")
    int travellingTimeMins;

    @Field("travelling-time-secs")
    int travellingTimeSecs;

    @Field("departure-time")
    TimeObject departureTime;

    @Field("arrival-time")
    TimeObject arrivalTime;

    @Field("owner-trip")
    Long ownerTrip;
}

@Data
class TimeObject implements Serializable{

    @Field("hour")
    int hour;

    @Field("minute")
    int minute;

    @Field("second")
    int second;
}
