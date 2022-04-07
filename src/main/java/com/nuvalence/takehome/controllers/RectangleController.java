package com.nuvalence.takehome.controllers;

import com.nuvalence.takehome.models.requests.RectanglesRelationParam;
import com.nuvalence.takehome.models.response.ShapesRelationResponse;
import com.nuvalence.takehome.services.RectangleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rectangle")
public class RectangleController {

    private final RectangleService rectangleService;

    @Autowired
    public RectangleController(RectangleService rectangleService) {
        this.rectangleService = rectangleService;
    }

    @PostMapping(value = "/relation", consumes = "application/json", produces = "application/json")
    public ShapesRelationResponse getRectanglesRelation(@RequestBody RectanglesRelationParam p) {
        return rectangleService.getRelation(p.getR1(), p.getR2());
    }
}
