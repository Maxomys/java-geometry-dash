package com.github.maxomys.geometrydash.components;

import com.github.maxomys.geometrydash.jade.Component;
import com.github.maxomys.geometrydash.jade.GameObject;
import com.github.maxomys.geometrydash.jade.Window;
import com.github.maxomys.geometrydash.util.Constants;
import com.github.maxomys.geometrydash.util.Vector2;

import java.awt.*;
import java.awt.event.MouseEvent;

public class SnapToGrid extends Component {

    private float debounceTime = 0.2f;
    private float debounceLeft = 0.0f;

    int gridWidth, gridHeight;

    public SnapToGrid(int gridWidth, int gridHeight) {
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
    }

    @Override
    public void update(double dt) {
        debounceLeft -= dt;

        if (this.gameObject.getComponent(Sprite.class) != null) {
            float x = (float) Math.floor((Window.getInstance().mouseListener.x +
                    Window.getInstance().getCurrentScene().camera.position.x + Window.getInstance().mouseListener.dx) / gridWidth);
            float y = (float) Math.floor((Window.getInstance().mouseListener.y +
                    Window.getInstance().getCurrentScene().camera.position.y + Window.getInstance().mouseListener.dy) / gridHeight);

            this.gameObject.transform.position.x = x * gridWidth - Window.getInstance().getCurrentScene().camera.position.x;
            this.gameObject.transform.position.y = y * gridHeight - Window.getInstance().getCurrentScene().camera.position.y;

            if (Window.getInstance().mouseListener.y < Constants.BUTTON_OFFSET_Y && Window.getInstance().mouseListener.mousePressed &&
                    Window.getInstance().mouseListener.mouseButton == MouseEvent.BUTTON1 && debounceLeft < 0) {

                debounceLeft = debounceTime;
                GameObject object = gameObject.copy();
                object.transform.position = new Vector2(x * gridWidth, y * gridHeight);
                Window.getInstance().getCurrentScene().addGameObject(object);
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        Sprite sprite = gameObject.getComponent(Sprite.class);
        if (sprite != null) {
            float alpha = 0.5f;
            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
            g2.setComposite(ac);
            g2.drawImage(sprite.image, (int) gameObject.transform.position.x, (int) gameObject.transform.position.y,
                    sprite.width, sprite.height, null);
            alpha = 1;
            ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
            g2.setComposite(ac);

        }
    }

    @Override
    public Component copy() {
        return null;
    }

}
