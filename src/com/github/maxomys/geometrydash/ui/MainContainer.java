package com.github.maxomys.geometrydash.ui;

import com.github.maxomys.geometrydash.components.Sprite;
import com.github.maxomys.geometrydash.components.Spritesheet;
import com.github.maxomys.geometrydash.dataStructure.AssetPool;
import com.github.maxomys.geometrydash.dataStructure.Transform;
import com.github.maxomys.geometrydash.jade.Component;
import com.github.maxomys.geometrydash.jade.GameObject;
import com.github.maxomys.geometrydash.util.Constants;
import com.github.maxomys.geometrydash.util.Vector2;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class MainContainer extends Component {

    public List<GameObject> menuItems;

    public MainContainer() {
        this.menuItems = new ArrayList<>();
        init();
    }

    private void init() {
        Spritesheet groundSprites = AssetPool.getSpritesheet("assets/spritesheet.png");
        Spritesheet buttonSprites = AssetPool.getSpritesheet("assets/ui/buttonSprites.png");

        for (int i = 0; i < groundSprites.sprites.size(); i++) {
            Sprite currentSprite = groundSprites.sprites.get(i);
            int x = Constants.BUTTON_OFFSET_X + currentSprite.column * Constants.BUTTON_WIDTH + currentSprite.column * Constants.BUTTON_SPACING_HZ;
            int y = Constants.BUTTON_OFFSET_Y + currentSprite.row * Constants.BUTTON_HEIGHT + currentSprite.row * Constants.BUTTON_SPACING_VT;

            GameObject obj = new GameObject("Generated", new Transform(new Vector2(x, y)));
            obj.addComponent(currentSprite.copy());
            MenuItem menuItem = new MenuItem(x, y, Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT, buttonSprites.sprites.get(0), buttonSprites.sprites.get(1));
            obj.addComponent(menuItem);
            menuItems.add(obj);
        }
    }

    @Override
    public void start() {
        for (GameObject g : menuItems) {
            for (Component c : g.getAllComponents()) {
                c.start();
            }
        }
    }

    @Override
    public void update(double dt) {
        for (GameObject g : this.menuItems) {
            g.update(dt);
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        for (GameObject g : this.menuItems) {
            g.draw(g2);
        }
    }

    @Override
    public Component copy() {
        return null;
    }

    @Override
    public String serialize(int tabSize) {
        return "";
    }

}
