package com.nuvalence.takehome.domain;

import java.awt.*;
import java.io.Serializable;
import java.util.Objects;

public class Rectangle extends Shape implements Serializable {

    private Point point;
    private int width;
    private int height;

    public Rectangle() {
    }

    public Rectangle(Point point, int width, int height) {
        this.point = point;
        this.width = width;
        this.height = height;
    }


    public Point getPoint() {
        return point;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return width == rectangle.width && height == rectangle.height && point.equals(rectangle.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, width, height);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "point=" + point.toString() +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
