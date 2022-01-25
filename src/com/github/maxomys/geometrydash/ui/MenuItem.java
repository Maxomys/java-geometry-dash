package com.github.maxomys.geometrydash.ui;

import com.github.maxomys.geometrydash.components.SnapToGrid;
import com.github.maxomys.geometrydash.components.Sprite;
import com.github.maxomys.geometrydash.jade.*;
import com.github.maxomys.geometrydash.jade.Component;
import com.github.maxomys.geometrydash.jade.Window;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MenuItem extends Component {

    public boolean isSelected;
    private int x, y, width, height;
    private Sprite buttonSprite, hoverSprite, myImage;
    private int bufferX, bufferY;

    public MenuItem(int x, int y, int width, int height, Sprite buttonSprite, Sprite hoverSprite) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.buttonSprite = buttonSprite;
        this.hoverSprite = hoverSprite;
        this.isSelected = false;
    }

    @Override
    public void start() {
        myImage = gameObject.getComponent(Sprite.class);

        this.bufferX = this.width / 2 - myImage.width / 2;
        this.bufferY = this.height / 2 - myImage.height / 2;
    }

    @Override
    public void update(double dt) {
        ML mouseListener = Window.getInstance().mouseListener;

        if (!isSelected && mouseListener.x > this.x && mouseListener.x <= this.x + this.width &&
                mouseListener.y > this.y && mouseListener.y <= this.y + this.height) {
            if (mouseListener.mousePressed && mouseListener.mouseButton == MouseEvent.BUTTON1) {

                //Clicked inside button
                GameObject obj = gameObject.copy();
                obj.removeComponent(MenuItem.class);
                LevelEditorScene scene = (LevelEditorScene) Window.getInstance().getCurrentScene();

                SnapToGrid snapToGrid = scene.mouseCursor.getComponent(SnapToGrid.class);
                obj.addComponent(snapToGrid);
                scene.mouseCursor = obj;

                isSelected = true;
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(this.buttonSprite.image, this.x, this.y, this.width, this.height, null);
        g2.drawImage(myImage.image, this.x + bufferX, this.y + bufferY, myImage.width, myImage.height, null);
        if (isSelected) {
            g2.drawImage(hoverSprite.image, this.x, this.y, this.width, this.height, null);
        }
    }

    @Override
    public Component copy() {
        return null;
    }

}
