package com.github.maxomys.geometrydash.components;

import com.github.maxomys.geometrydash.jade.Component;
import com.github.maxomys.geometrydash.jade.Window;

import java.awt.event.MouseEvent;


public class CameraControls extends Component {

    private float prevMx, prevMy;

    public CameraControls() {
        this.prevMx = 0;
        this.prevMy = 0;
    }

    @Override
    public void update(double dt) {
        Window window = Window.getInstance();

        if (window.mouseListener.mousePressed && window.mouseListener.mouseButton == MouseEvent.BUTTON2) {
            float dx = window.mouseListener.x + window.mouseListener.dx - prevMx;
            float dy = window.mouseListener.y + window.mouseListener.dy - prevMy;

            window.getCurrentScene().camera.position.x -= dx;
            window.getCurrentScene().camera.position.y -= dy;
        }

        prevMx = window.mouseListener.x + window.mouseListener.dx;
        prevMy = window.mouseListener.y + window.mouseListener.dy;
    }

}
