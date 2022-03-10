package com.github.maxomys.geometrydash.jade;

import com.github.maxomys.geometrydash.components.*;
import com.github.maxomys.geometrydash.dataStructure.AssetPool;
import com.github.maxomys.geometrydash.dataStructure.Transform;
import com.github.maxomys.geometrydash.ui.MainContainer;
import com.github.maxomys.geometrydash.util.Constants;
import com.github.maxomys.geometrydash.util.Vector2;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class LevelEditorScene extends Scene {

    public GameObject playerGO;
    public GameObject mouseCursor;
    private GameObject groundGO;
    private Grid grid;
    private CameraControls cameraControls;
    private MainContainer editingButtons;

    public LevelEditorScene(String name) {
        super(name);
    }

    @Override
    public void init() {
        initAssetPool();
        editingButtons = new MainContainer();

        grid = new Grid();
        cameraControls = new CameraControls();
        editingButtons.start();

        mouseCursor = new GameObject("Mouse Cursor", new Transform(new Vector2()));
        mouseCursor.addComponent(new SnapToGrid(Constants.TILE_WIDTH, Constants.TILE_HEIGHT));

        playerGO = new GameObject("Test object", new Transform(new Vector2(550.0f, 400.0f)));
        Spritesheet layerOne = AssetPool.getSpritesheet("assets/player/layerOne.png");
        Spritesheet layerTwo = AssetPool.getSpritesheet("assets/player/layerTwo.png");
        Spritesheet layerThree = AssetPool.getSpritesheet("assets/player/layerThree.png");

        Player playerComp = new Player(layerOne.sprites.get(0), layerTwo.sprites.get(0), layerThree.sprites.get(0), Color.RED, Color.BLUE);
        playerGO.addComponent(playerComp);

        groundGO = new GameObject("Ground", new Transform(new Vector2(0, Constants.GROUND_Y)));
        groundGO.addComponent(new Ground());

        groundGO.setNonSerializable();
        playerGO.setNonSerializable();
        addGameObject(playerGO);
        addGameObject(groundGO);
    }

    private void initAssetPool() {
        AssetPool.addSpritesheet("assets/player/layerOne.png", 42, 42, 2, 13, 13 * 5);
        AssetPool.addSpritesheet("assets/player/layerTwo.png", 42, 42, 2, 13, 13 * 5);
        AssetPool.addSpritesheet("assets/player/layerThree.png", 42, 42, 2, 13, 13 * 5);
        AssetPool.addSpritesheet("assets/spritesheet.png", 42, 42, 2, 6, 12);
        AssetPool.addSpritesheet("assets/ui/buttonSprites.png", 60, 60, 2, 2, 2);
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

        if (Window.getInstance().keyListener.isKeyPressed(KeyEvent.VK_F1)) {
            export("test");
        }
    }

    private void export(String fileName) {
        try {
            FileOutputStream fos = new FileOutputStream("levels/" + fileName + ".zip");
            ZipOutputStream zos = new ZipOutputStream(fos);

            zos.putNextEntry(new ZipEntry(fileName + ".json"));

            int i = 0;
            for (GameObject go : gameObjects) {
                String str = go.serialize(0);
                if (str.compareTo("") != 0) {
                    zos.write(str.getBytes());
                    if (i != gameObjects.size() - 1) {
                        zos.write(",\n".getBytes());
                    }
                }
                i++;
            }

            zos.closeEntry();
            zos.close();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
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
