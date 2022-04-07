package com.nuvalence.takehome.services;

import com.nuvalence.takehome.domain.Rectangle;
import com.nuvalence.takehome.models.requests.RectanglesRelationParam;
import com.nuvalence.takehome.models.response.ShapesRelationResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.*;

import static com.nuvalence.takehome.domain.Adjacency.*;

@SpringBootTest
public class RectangleServiceTest {

    @Autowired
    RectangleService rectangleService;

    @Test
    public void test_getRelation_contains() {
        RectanglesRelationParam rectanglesRelationParam = new RectanglesRelationParam();
        Rectangle r1 = new Rectangle(new Point(200,200),200,200);
        Rectangle r2 = new Rectangle(new Point(250, 150),100,100);
        rectanglesRelationParam.setR1(r1);
        rectanglesRelationParam.setR2(r2);

        ShapesRelationResponse shapesRelationResponse=  rectangleService.getRelation(r1,r2);

        //Verify response
        Assertions.assertEquals(NOT_ADJACENT,shapesRelationResponse.getAdjacency());
        Assertions.assertEquals(Boolean.FALSE,shapesRelationResponse.isIntersects());
        Assertions.assertEquals(Boolean.TRUE,shapesRelationResponse.isContains());
        Assertions.assertNull(shapesRelationResponse.getIntersectionPoints());
    }

    @Test
    public void test_getRelation_contains_same_rectangles() {
        RectanglesRelationParam rectanglesRelationParam = new RectanglesRelationParam();
        Rectangle r1 = new Rectangle(new Point(200,200),200,200);
        Rectangle r2 = new Rectangle(new Point(200, 200),200,200);
        rectanglesRelationParam.setR1(r1);
        rectanglesRelationParam.setR2(r2);

        ShapesRelationResponse shapesRelationResponse=  rectangleService.getRelation(r1,r2);

        //Verify response
        Assertions.assertEquals(NOT_ADJACENT,shapesRelationResponse.getAdjacency());
        Assertions.assertEquals(Boolean.FALSE,shapesRelationResponse.isIntersects());
        Assertions.assertEquals(Boolean.TRUE,shapesRelationResponse.isContains());
        Assertions.assertNull(shapesRelationResponse.getIntersectionPoints());
    }

    @Test
    public void test_getRelation_contains_false() {
        RectanglesRelationParam rectanglesRelationParam = new RectanglesRelationParam();
        Rectangle r1 = new Rectangle(new Point(200,200),200,200);
        Rectangle r2 = new Rectangle(new Point(0, 200),100,200);
        rectanglesRelationParam.setR1(r1);
        rectanglesRelationParam.setR2(r2);

        ShapesRelationResponse shapesRelationResponse=  rectangleService.getRelation(r1,r2);

        //Verify response
        Assertions.assertEquals(NOT_ADJACENT,shapesRelationResponse.getAdjacency());
        Assertions.assertEquals(Boolean.FALSE,shapesRelationResponse.isIntersects());
        Assertions.assertEquals(Boolean.FALSE,shapesRelationResponse.isContains());
        Assertions.assertNull(shapesRelationResponse.getIntersectionPoints());
    }

    @Test
    public void test_getRelation_intersects_left_true() {
        RectanglesRelationParam rectanglesRelationParam = new RectanglesRelationParam();
        Rectangle r1 = new Rectangle(new Point(200,200),200,200);
        Rectangle r2 = new Rectangle(new Point(100, 100),200,50);
        rectanglesRelationParam.setR1(r1);
        rectanglesRelationParam.setR2(r2);

        ShapesRelationResponse shapesRelationResponse=  rectangleService.getRelation(r1,r2);

        //Verify response
        Assertions.assertEquals(NOT_ADJACENT,shapesRelationResponse.getAdjacency());
        Assertions.assertEquals(Boolean.TRUE,shapesRelationResponse.isIntersects());
        Assertions.assertEquals(Boolean.FALSE,shapesRelationResponse.isContains());
        Assertions.assertEquals(2, shapesRelationResponse.getIntersectionPoints().size());
        Assertions.assertEquals(new Point(200,100), shapesRelationResponse.getIntersectionPoints().get(0));
        Assertions.assertEquals(new Point(200,50), shapesRelationResponse.getIntersectionPoints().get(1));
    }

    @Test
    public void test_getRelation_intersects_right_true() {
        RectanglesRelationParam rectanglesRelationParam = new RectanglesRelationParam();
        Rectangle r1 = new Rectangle(new Point(200,200),200,200);
        Rectangle r2 = new Rectangle(new Point(300, 100),200,50);
        rectanglesRelationParam.setR1(r1);
        rectanglesRelationParam.setR2(r2);

        ShapesRelationResponse shapesRelationResponse=  rectangleService.getRelation(r1,r2);

        //Verify response
        Assertions.assertEquals(NOT_ADJACENT,shapesRelationResponse.getAdjacency());
        Assertions.assertEquals(Boolean.TRUE,shapesRelationResponse.isIntersects());
        Assertions.assertEquals(Boolean.FALSE,shapesRelationResponse.isContains());
        Assertions.assertEquals(2, shapesRelationResponse.getIntersectionPoints().size());
        Assertions.assertEquals(new Point(400,100), shapesRelationResponse.getIntersectionPoints().get(0));
        Assertions.assertEquals(new Point(400,50), shapesRelationResponse.getIntersectionPoints().get(1));
    }

    @Test
    public void test_getRelation_intersects_top_true() {
        RectanglesRelationParam rectanglesRelationParam = new RectanglesRelationParam();
        Rectangle r1 = new Rectangle(new Point(200,200),200,200);
        Rectangle r2 = new Rectangle(new Point(300, 300),100,200);
        rectanglesRelationParam.setR1(r1);
        rectanglesRelationParam.setR2(r2);

        ShapesRelationResponse shapesRelationResponse=  rectangleService.getRelation(r1,r2);

        //Verify response
        Assertions.assertEquals(NOT_ADJACENT,shapesRelationResponse.getAdjacency());
        Assertions.assertEquals(Boolean.TRUE,shapesRelationResponse.isIntersects());
        Assertions.assertEquals(Boolean.FALSE,shapesRelationResponse.isContains());
        Assertions.assertEquals(1, shapesRelationResponse.getIntersectionPoints().size());
        Assertions.assertEquals(new Point(300,200), shapesRelationResponse.getIntersectionPoints().get(0));
    }

    @Test
    public void test_getRelation_intersects_top_right__true() {
        RectanglesRelationParam rectanglesRelationParam = new RectanglesRelationParam();
        Rectangle r1 = new Rectangle(new Point(200,200),200,200);
        Rectangle r2 = new Rectangle(new Point(300, 300),300,200);
        rectanglesRelationParam.setR1(r1);
        rectanglesRelationParam.setR2(r2);

        ShapesRelationResponse shapesRelationResponse=  rectangleService.getRelation(r1,r2);

        //Verify response
        Assertions.assertEquals(NOT_ADJACENT,shapesRelationResponse.getAdjacency());
        Assertions.assertEquals(Boolean.TRUE,shapesRelationResponse.isIntersects());
        Assertions.assertEquals(Boolean.FALSE,shapesRelationResponse.isContains());
        Assertions.assertEquals(2, shapesRelationResponse.getIntersectionPoints().size());
        Assertions.assertEquals(new Point(400,100), shapesRelationResponse.getIntersectionPoints().get(0));
        Assertions.assertEquals(new Point(300,200), shapesRelationResponse.getIntersectionPoints().get(1));
    }

    @Test
    public void test_getRelation_intersects_bottom_top_true() {
        RectanglesRelationParam rectanglesRelationParam = new RectanglesRelationParam();
        Rectangle r1 = new Rectangle(new Point(200,200),200,200);
        Rectangle r2 = new Rectangle(new Point(300, 300),300,400);
        rectanglesRelationParam.setR1(r1);
        rectanglesRelationParam.setR2(r2);

        ShapesRelationResponse shapesRelationResponse=  rectangleService.getRelation(r1,r2);

        //Verify response
        Assertions.assertEquals(NOT_ADJACENT,shapesRelationResponse.getAdjacency());
        Assertions.assertEquals(Boolean.TRUE,shapesRelationResponse.isIntersects());
        Assertions.assertEquals(Boolean.FALSE,shapesRelationResponse.isContains());
        Assertions.assertEquals(2, shapesRelationResponse.getIntersectionPoints().size());
        Assertions.assertEquals(new Point(300,200), shapesRelationResponse.getIntersectionPoints().get(0));
        Assertions.assertEquals(new Point(300,0), shapesRelationResponse.getIntersectionPoints().get(1));
    }

    @Test
    public void test_getRelation_intersects_bottom_right_true() {
        RectanglesRelationParam rectanglesRelationParam = new RectanglesRelationParam();
        Rectangle r1 = new Rectangle(new Point(200,200),200,200);
        Rectangle r2 = new Rectangle(new Point(300, 100),200,200);
        rectanglesRelationParam.setR1(r1);
        rectanglesRelationParam.setR2(r2);

        ShapesRelationResponse shapesRelationResponse=  rectangleService.getRelation(r1,r2);

        //Verify response
        Assertions.assertEquals(NOT_ADJACENT,shapesRelationResponse.getAdjacency());
        Assertions.assertEquals(Boolean.TRUE,shapesRelationResponse.isIntersects());
        Assertions.assertEquals(Boolean.FALSE,shapesRelationResponse.isContains());
        Assertions.assertEquals(2, shapesRelationResponse.getIntersectionPoints().size());
        Assertions.assertEquals(new Point(400,100), shapesRelationResponse.getIntersectionPoints().get(0));
        Assertions.assertEquals(new Point(300,0), shapesRelationResponse.getIntersectionPoints().get(1));
    }

    @Test
    public void test_getRelation_adjacency_not_adjacent(){
        RectanglesRelationParam rectanglesRelationParam = new RectanglesRelationParam();
        Rectangle r1 = new Rectangle(new Point(200,200),200,200);
        Rectangle r2 = new Rectangle(new Point(300, 100),200,200);
        rectanglesRelationParam.setR1(r1);
        rectanglesRelationParam.setR2(r2);

        ShapesRelationResponse shapesRelationResponse=  rectangleService.getRelation(r1,r2);

        //Verify response
        Assertions.assertEquals(NOT_ADJACENT,shapesRelationResponse.getAdjacency());
        Assertions.assertEquals(Boolean.TRUE,shapesRelationResponse.isIntersects());
        Assertions.assertEquals(Boolean.FALSE,shapesRelationResponse.isContains());
        Assertions.assertEquals(2, shapesRelationResponse.getIntersectionPoints().size());
    }

    @Test
    public void test_getRelation_adjacency_inside_other_rectangle(){
        RectanglesRelationParam rectanglesRelationParam = new RectanglesRelationParam();
        Rectangle r1 = new Rectangle(new Point(200,200),200,200);
        Rectangle r2 = new Rectangle(new Point(300, 100),100,100);
        rectanglesRelationParam.setR1(r1);
        rectanglesRelationParam.setR2(r2);

        ShapesRelationResponse shapesRelationResponse=  rectangleService.getRelation(r1,r2);

        //Verify response
        Assertions.assertEquals(NOT_ADJACENT,shapesRelationResponse.getAdjacency());
        Assertions.assertEquals(Boolean.FALSE,shapesRelationResponse.isIntersects());
        Assertions.assertEquals(Boolean.TRUE,shapesRelationResponse.isContains());
        Assertions.assertNull(shapesRelationResponse.getIntersectionPoints());
    }

    @Test
    public void test_getRelation_adjacency_proper_vertical(){
        RectanglesRelationParam rectanglesRelationParam = new RectanglesRelationParam();
        Rectangle r1 = new Rectangle(new Point(200,200),200,200);
        Rectangle r2 = new Rectangle(new Point(400, 200),200,200);
        rectanglesRelationParam.setR1(r1);
        rectanglesRelationParam.setR2(r2);

        ShapesRelationResponse shapesRelationResponse=  rectangleService.getRelation(r1,r2);

        //Verify response
        Assertions.assertEquals(PROPER,shapesRelationResponse.getAdjacency());
        Assertions.assertEquals(Boolean.FALSE,shapesRelationResponse.isIntersects());
        Assertions.assertEquals(Boolean.FALSE,shapesRelationResponse.isContains());
        Assertions.assertNull(shapesRelationResponse.getIntersectionPoints());
    }

    @Test
    public void test_getRelation_adjacency_proper_horizontal(){
        RectanglesRelationParam rectanglesRelationParam = new RectanglesRelationParam();
        Rectangle r1 = new Rectangle(new Point(200,200),200,200);
        Rectangle r2 = new Rectangle(new Point(200, 400),200,200);
        rectanglesRelationParam.setR1(r1);
        rectanglesRelationParam.setR2(r2);

        ShapesRelationResponse shapesRelationResponse=  rectangleService.getRelation(r1,r2);

        //Verify response
        Assertions.assertEquals(PROPER,shapesRelationResponse.getAdjacency());
        Assertions.assertEquals(Boolean.FALSE,shapesRelationResponse.isIntersects());
        Assertions.assertEquals(Boolean.FALSE,shapesRelationResponse.isContains());
        Assertions.assertNull(shapesRelationResponse.getIntersectionPoints());
    }

    @Test
    public void test_getRelation_adjacency_sub_line_vertical(){
        RectanglesRelationParam rectanglesRelationParam = new RectanglesRelationParam();
        Rectangle r1 = new Rectangle(new Point(200,200),200,200);
        Rectangle r2 = new Rectangle(new Point(400, 100),200,50);
        rectanglesRelationParam.setR1(r1);
        rectanglesRelationParam.setR2(r2);

        ShapesRelationResponse shapesRelationResponse=  rectangleService.getRelation(r1,r2);

        //Verify response
        Assertions.assertEquals(SUB_LINE,shapesRelationResponse.getAdjacency());
        Assertions.assertEquals(Boolean.FALSE,shapesRelationResponse.isIntersects());
        Assertions.assertEquals(Boolean.FALSE,shapesRelationResponse.isContains());
        Assertions.assertNull(shapesRelationResponse.getIntersectionPoints());
    }

    @Test
    public void test_getRelation_adjacency_sub_line_horizontal(){
        RectanglesRelationParam rectanglesRelationParam = new RectanglesRelationParam();
        Rectangle r1 = new Rectangle(new Point(200,200),200,200);
        Rectangle r2 = new Rectangle(new Point(300, 400),50,200);
        rectanglesRelationParam.setR1(r1);
        rectanglesRelationParam.setR2(r2);

        ShapesRelationResponse shapesRelationResponse=  rectangleService.getRelation(r1,r2);

        //Verify response
        Assertions.assertEquals(SUB_LINE,shapesRelationResponse.getAdjacency());
        Assertions.assertEquals(Boolean.FALSE,shapesRelationResponse.isIntersects());
        Assertions.assertEquals(Boolean.FALSE,shapesRelationResponse.isContains());
        Assertions.assertNull(shapesRelationResponse.getIntersectionPoints());
    }

    @Test
    public void test_getRelation_adjacency_sub_line_horizontal_edge_case(){
        RectanglesRelationParam rectanglesRelationParam = new RectanglesRelationParam();
        Rectangle r1 = new Rectangle(new Point(200,200),200,200);
        Rectangle r2 = new Rectangle(new Point(300, 400),100,200);
        rectanglesRelationParam.setR1(r1);
        rectanglesRelationParam.setR2(r2);

        ShapesRelationResponse shapesRelationResponse=  rectangleService.getRelation(r1,r2);

        //Verify response
        Assertions.assertEquals(SUB_LINE,shapesRelationResponse.getAdjacency());
        Assertions.assertEquals(Boolean.FALSE,shapesRelationResponse.isIntersects());
        Assertions.assertEquals(Boolean.FALSE,shapesRelationResponse.isContains());
        Assertions.assertNull(shapesRelationResponse.getIntersectionPoints());
    }

    @Test
    public void test_getRelation_adjacency_partial_horizontal(){
        RectanglesRelationParam rectanglesRelationParam = new RectanglesRelationParam();
        Rectangle r1 = new Rectangle(new Point(200,200),200,200);
        Rectangle r2 = new Rectangle(new Point(300, 400),200,200);
        rectanglesRelationParam.setR1(r1);
        rectanglesRelationParam.setR2(r2);

        ShapesRelationResponse shapesRelationResponse=  rectangleService.getRelation(r1,r2);

        //Verify response
        Assertions.assertEquals(PARTIAL,shapesRelationResponse.getAdjacency());
        Assertions.assertEquals(Boolean.FALSE,shapesRelationResponse.isIntersects());
        Assertions.assertEquals(Boolean.FALSE,shapesRelationResponse.isContains());
        Assertions.assertNull(shapesRelationResponse.getIntersectionPoints());
    }


}
