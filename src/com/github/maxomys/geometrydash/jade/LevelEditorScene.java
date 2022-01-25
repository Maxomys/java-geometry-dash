package com.github.maxomys.geometrydash.jade;

import com.github.maxomys.geometrydash.components.*;
import com.github.maxomys.geometrydash.dataStructure.Transform;
import com.github.maxomys.geometrydash.ui.MainContainer;
import com.github.maxomys.geometrydash.util.Constants;
import com.github.maxomys.geometrydash.util.Vector2;

import java.awt.*;

public class LevelEditorScene extends Scene {

    public GameObject playerGO;
    public GameObject mouseCursor;
    private GameObject groundGO;
    private Grid grid;
    private CameraControls cameraControls;
    private MainContainer editingButtons = new MainContainer();

    public LevelEditorScene(String name) {
        super(name);
    }

    @Override
    public void init() {
        grid = new Grid();
        cameraControls = new CameraControls();
        editingButtons.start();

        mouseCursor = new GameObject("Mouse Cursor", new Transform(new Vector2()));
        mouseCursor.addComponent(new SnapToGrid(Constants.TILE_WIDTH, Constants.TILE_HEIGHT));

        playerGO = new GameObject("Test object", new Transform(new Vector2(550.0f, 400.0f)));
        Spritesheet layerOne = new Spritesheet("assets/player/layerOne.png", 42, 42, 2, 13, 13 * 5);
        Spritesheet layerTwo = new Spritesheet("assets/player/layerTwo.png", 42, 42, 2, 13, 13 * 5);
        Spritesheet layerThree = new Spritesheet("assets/player/layerThree.png", 42, 42, 2, 13, 13 * 5);

        Player playerComp = new Player(layerOne.sprites.get(0), layerTwo.sprites.get(0), layerThree.sprites.get(0), Color.RED, Color.BLUE);
        playerGO.addComponent(playerComp);

        groundGO = new GameObject("Ground", new Transform(new Vector2(0, Constants.GROUND_Y)));
        groundGO.addComponent(new Ground());

        addGameObject(playerGO);
        addGameObject(groundGO);
    }

    @Override
    public void update(double dt) {
        if (camera.position.y > Constants.CAMERA_OFFSET_GROUND_Y) {
            camera.position.y = Constants.CAMERA_OFFSET_GROUND_Y;
        }

        for (GameObject g : gameObjects) {
            g.update(dt);
        }

        cameraControls.update(dt);
        grid.update(dt);
        editingButtons.update(dt);
        mouseCursor.update(dt);
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(new Color(76, 76, 76));
        g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        renderer.render(g2);
        grid.draw(g2);
        editingButtons.draw(g2);
        mouseCursor.draw(g2);
    }

}
