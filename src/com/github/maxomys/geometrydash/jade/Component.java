package com.github.maxomys.geometrydash.jade;

import java.awt.Graphics2D;

public abstract class Component {

    public GameObject gameObject;

    public void update(double dt) {

    }

    public void draw(Graphics2D g2) {

    }

    public abstract Component copy();

}
