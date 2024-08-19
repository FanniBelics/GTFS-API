package com.unideb.hu.gtfsapi.entities;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.annotation.Id;
import java.io.Serializable;
import java.util.List;


@Data
@Document(collection = "solutions")
public class SolutionEntity{
    @Id
    @Field("_id")
    ObjectId id;

    @Field("from-id")
    Integer fromId;

    @Field("to-id")
    Integer toId;

    @Field("changes")
    List<List<Change>> changes;

}

@Data
class Change implements Serializable {
    @Field("from-stop-partial")
    Long fromStopPartial;

    @Field("to-stop-partial")
    Long toStopPartial;

    @Field("route-id")
    Long routeId;
}