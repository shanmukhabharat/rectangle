package com.nuvalence.takehome.models.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nuvalence.takehome.domain.Adjacency;

import java.awt.*;
import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShapesRelationResponse implements Serializable {

    private boolean intersects;
    private boolean contains;
    private List<Point> intersectionPoints;
    private Adjacency adjacency;

    public Adjacency getAdjacency() {
        return adjacency;
    }

    public void setAdjacency(Adjacency adjacency) {
        this.adjacency = adjacency;
    }

    public boolean isIntersects() {
        return intersects;
    }

    public void setIntersects(boolean intersects) {
        this.intersects = intersects;
    }

    public boolean isContains() {
        return contains;
    }

    public void setContains(boolean contains) {
        this.contains = contains;
    }

    public List<Point> getIntersectionPoints() {
        return intersectionPoints;
    }

    public void setIntersectionPoints(List<Point> intersectionPoints) {
        this.intersectionPoints = intersectionPoints;
    }
}
