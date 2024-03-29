package com.github.maxomys.geometrydash.jade;

import com.github.maxomys.geometrydash.file.Serialize;

import java.awt.Graphics2D;

public abstract class Component extends Serialize {

    public GameObject gameObject;

    public void start() {

    };

    public void update(double dt) {

    }

    public void draw(Graphics2D g2) {

    }

    public abstract Component copy();

    @Override
    public abstract String serialize(int tabSize);

}
