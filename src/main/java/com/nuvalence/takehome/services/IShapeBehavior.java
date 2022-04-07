package com.nuvalence.takehome.services;

import com.nuvalence.takehome.domain.Adjacency;
import com.nuvalence.takehome.domain.Shape;

import java.awt.*;
import java.util.List;

public interface IShapeBehavior {
    boolean intersects(Shape s1, Shape s2);
    List<Point> intersection(Shape s1, Shape s2);
    boolean contains(Shape s1, Shape s2);
    Adjacency adjacency(Shape s1, Shape s2);
}
