package com.unideb.hu.gtfsapi.entities;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "nodes")
public class ChildNodeEntity extends NodeEntity{
    @Field("parental-node")
    long motherNode;
}
