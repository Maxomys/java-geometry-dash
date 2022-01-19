package com.github.maxomys.geometrydash.jade;

import com.github.maxomys.geometrydash.components.*;
import com.github.maxomys.geometrydash.dataStructure.Transform;
import com.github.maxomys.geometrydash.util.Constants;
import com.github.maxomys.geometrydash.util.Vector2;

import java.awt.*;

public class LevelScene extends Scene {

    public GameObject playerGO;

    public LevelScene(String name) {
        super(name);
    }

    @Override
    public void init() {
        playerGO = new GameObject("Test object", new Transform(new Vector2(550.0f, 400.0f)));
        Spritesheet layerOne = new Spritesheet("assets/player/layerOne.png", 42, 42, 2, 13, 13 * 5);
        Spritesheet layerTwo = new Spritesheet("assets/player/layerTwo.png", 42, 42, 2, 13, 13 * 5);
        Spritesheet layerThree = new Spritesheet("assets/player/layerThree.png", 42, 42, 2, 13, 13 * 5);

        Player playerComp = new Player(layerOne.sprites.get(0), layerTwo.sprites.get(0), layerThree.sprites.get(0), Color.RED, Color.BLUE);
        playerGO.addComponent(playerComp);
        playerGO.addComponent(new RigidBody(new Vector2(395, 0)));
        playerGO.addComponent(new BoxBounds(Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT));
        gameObjects.add(playerGO);

        GameObject groundGO;
        groundGO = new GameObject("Ground", new Transform(new Vector2(0, Constants.GROUND_Y)));
        groundGO.addComponent(new Ground());
        gameObjects.add(groundGO);

        renderer.submit(playerGO);
        renderer.submit(groundGO);
    }

    @Override
    public void update(double dt) {
        if (playerGO.transform.position.x - camera.position.x > Constants.CAMERA_OFFSET_X) {
            camera.position.x = playerGO.transform.position.x - Constants.CAMERA_OFFSET_X;
        }

        if (playerGO.transform.position.y - camera.position.y > Constants.CAMERA_OFFSET_Y) {
            camera.position.y = playerGO.transform.position.y - Constants.CAMERA_OFFSET_Y;
        }

        if (camera.position.y > Constants.CAMERA_OFFSET_GROUND_Y) {
            camera.position.y = Constants.CAMERA_OFFSET_GROUND_Y;
        }

        for (GameObject g : gameObjects) {
            g.update(dt);
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(new Color(76, 76, 76));
        g2.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        renderer.render(g2);
    }
    
}
