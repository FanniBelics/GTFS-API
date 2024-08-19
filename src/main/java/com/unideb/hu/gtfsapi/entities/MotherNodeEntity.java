package com.unideb.hu.gtfsapi.entities;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "nodes")
public class MotherNodeEntity extends NodeEntity{
    @Field("children")
    List<Long> children;
}
