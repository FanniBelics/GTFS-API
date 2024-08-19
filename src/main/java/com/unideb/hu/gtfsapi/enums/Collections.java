package com.unideb.hu.gtfsapi.enums;

public enum Collections {
    SOLUTIONS("solutions"),
    TRIPS("trips"),
    EDGES("edges"),
    NODES("nodes"),
    ROUTES("routes");

    private String name;

    Collections(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
