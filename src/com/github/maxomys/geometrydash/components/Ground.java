package com.github.maxomys.geometrydash.components;

import com.github.maxomys.geometrydash.jade.*;
import com.github.maxomys.geometrydash.jade.Component;
import com.github.maxomys.geometrydash.jade.Window;
import com.github.maxomys.geometrydash.util.Constants;

import java.awt.Graphics2D;
import java.awt.Color;

public class Ground extends Component {

    @Override
    public void update(double dt) {
        if (!Window.getInstance().isInEditor) {
            LevelScene scene = (LevelScene) Window.getInstance().getCurrentScene();
            GameObject player = scene.playerGO;

            if (player.transform.position.y + player.getComponent(BoxBounds.class).height > gameObject.transform.position.y) {
                player.transform.position.y = gameObject.transform.position.y - player.getComponent(BoxBounds.class).height;
            }

            gameObject.transform.position.x = scene.camera.position.x - 10;
        } else {
            gameObject.transform.position.x = Window.getInstance().getCurrentScene().camera.position.x - 10;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.drawRect((int) gameObject.transform.position.x + 10, (int) gameObject.transform.position.y,
                Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

    }

}
