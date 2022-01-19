package com.github.maxomys.geometrydash.jade;

import com.github.maxomys.geometrydash.util.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Scene {

    String name;
    public Camera camera;
    List<GameObject> gameObjects;
    Renderer renderer;

    public Scene(String name) {
        this.name = name;
        this.camera = new Camera(new Vector2());
        this.gameObjects = new ArrayList<>();
        this.renderer = new Renderer(this.camera);
        init();
    }

    public abstract void init();

    public void addGameObject(GameObject g) {
        gameObjects.add(g);
        renderer.submit(g);
    }

    public abstract void update(double dt);

    public abstract void draw(Graphics2D g2);

}
