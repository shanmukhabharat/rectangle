package com.nuvalence.takehome.models.requests;

import com.nuvalence.takehome.domain.Rectangle;

public class RectanglesRelationParam {

    private Rectangle r1;
    private Rectangle r2;

    public Rectangle getR1() {
        return r1;
    }

    public void setR1(Rectangle r1) {
        this.r1 = r1;
    }

    public Rectangle getR2() {
        return r2;
    }

    public void setR2(Rectangle r2) {
        this.r2 = r2;
    }
}
