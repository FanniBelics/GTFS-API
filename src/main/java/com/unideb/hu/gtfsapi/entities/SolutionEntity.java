package com.unideb.hu.gtfsapi.entities;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.annotation.Id;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Document(collection = "solutions")
public class SolutionEntity{
    @Id
    @Field("_id")
    UUID id;

    @Field("from-id")
    Integer fromId;

    @Field("to-id")
    Integer toId;

    @Field("changes")
    List<Change> changes;

}

class Change implements Serializable {
    @Field("from-stop-partial")
    Long fromStopPartial;

    @Field("to-stop-partial")
    Long toStopPartial;

    @Field("route-id")
    Long routeId;
}