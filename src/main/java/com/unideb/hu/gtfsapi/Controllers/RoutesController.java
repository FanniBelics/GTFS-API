package com.unideb.hu.gtfsapi.Controllers;

import com.unideb.hu.gtfsapi.Services.RoutesService;
import com.unideb.hu.gtfsapi.entities.RouteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes/{database}")
public class RoutesController {

    @Autowired
    RoutesService service;

    @GetMapping("/find-by-id")
    public ResponseEntity<RouteEntity> findById(@PathVariable String database,
                                                @RequestParam long id){
        return service.findById(database, id);
    }

    @GetMapping("/find-by-route-type")
    public ResponseEntity<List<RouteEntity>> findByType(@PathVariable String database,
                                                        @RequestParam int type){
        return service.findByType(database, type);
    }

    @GetMapping("/find-by-name")
    public ResponseEntity<List<RouteEntity>> findByName(@PathVariable String database,
                                                        @RequestParam String name)
    {
        return service.findByName(database, name);
    }

    @GetMapping("/find-if-contains-stop")
    public ResponseEntity<List<RouteEntity>> findIfContainsStop(@PathVariable String database,
                                                                @RequestParam long stopId)
    {
        return service.findIfContainsStop(database, stopId);
    }

    @PostMapping("/count")
    public ResponseEntity<Long> count(@PathVariable String database,
                                      @RequestBody String query)
    {
        return service.count(database, query);
    }
}
