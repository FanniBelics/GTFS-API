package com.unideb.hu.gtfsapi.Controllers;

import com.unideb.hu.gtfsapi.Services.StopsService;
import com.unideb.hu.gtfsapi.entities.NodeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stops/{database}")
public class StopsController {

    @Autowired
    StopsService service;

    @GetMapping("/find-by-name")
    public ResponseEntity<List<NodeEntity>> findByName(@PathVariable String database,
                                                       @RequestParam String name) {
        return service.findByName(database, name);
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<NodeEntity> findById(@PathVariable String database,
                                               @RequestParam long id) {
        return service.findByGtfsId(database, id);
    }

    @PostMapping("/count")
    public ResponseEntity<Long> countStops(@PathVariable String database,
                                            @RequestBody String query)
    {
        return service.countStops(database, query);
    }

}
