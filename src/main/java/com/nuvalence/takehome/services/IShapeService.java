package com.nuvalence.takehome.services;

import com.nuvalence.takehome.domain.Shape;
import com.nuvalence.takehome.models.response.ShapesRelationResponse;

public interface IShapeService extends IShapeBehavior {
    ShapesRelationResponse getRelation(Shape s1, Shape s2);
}
