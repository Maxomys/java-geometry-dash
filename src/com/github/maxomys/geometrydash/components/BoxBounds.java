package com.github.maxomys.geometrydash.components;

import com.github.maxomys.geometrydash.jade.Component;

public class BoxBounds extends Component {

    public float width, height;

    public BoxBounds(float width, float height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void update(double dt) {

    }

    @Override
    public Component copy() {
        return new BoxBounds(width, height);
    }

}
