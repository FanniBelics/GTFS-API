package com.unideb.hu.gtfsapi.Controllers;

import com.unideb.hu.gtfsapi.Services.SolutionService;
import com.unideb.hu.gtfsapi.entities.SolutionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/paths/{database}")
public class SolutionController {

    @Autowired
    SolutionService service;

    @GetMapping("/find-by-stops")
    public ResponseEntity<SolutionEntity> findByStops(@PathVariable String database,
                                                      @RequestParam long fromStop,
                                                      @RequestParam long toStop)
    {
        return service.findByStops(database, fromStop, toStop);
    }

    @GetMapping("/find-by-from-stop")
    public ResponseEntity<List<SolutionEntity>> findByFromStop(@PathVariable String database,
                                                               @RequestParam long fromStop)
    {
        return service.findByFromStop(database, fromStop);
    }

    @GetMapping("/find-by-to-stop")
    public ResponseEntity<List<SolutionEntity>> findByToStop(@PathVariable String database,
                                                             @RequestParam long toStop)
    {
        return service.findByToStop(database, toStop);
    }
}
