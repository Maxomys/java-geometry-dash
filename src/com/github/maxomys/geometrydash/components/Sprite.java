package com.github.maxomys.geometrydash.components;

import com.github.maxomys.geometrydash.dataStructure.AssetPool;
import com.github.maxomys.geometrydash.jade.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Sprite extends Component {

    public BufferedImage image;
    public String pictureFile;
    public int width, height;

    public boolean isSubSprite = false;
    public int row, column, index;

    public Sprite(String pictureFile) {
        this.pictureFile = pictureFile;

        try {
            File file = new File(pictureFile);

            if (AssetPool.hasSprite(pictureFile)) {
                throw new Exception("Asset already exists: " + pictureFile);
            }

            this.image = ImageIO.read(file);
            this.width = image.getWidth();
            this.height = image.getHeight();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Sprite(BufferedImage image, String pictureFile) {
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();

        this.pictureFile = pictureFile;
    }

    public Sprite(BufferedImage image, int row, int column, int index, String pictureFile) {
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();

        this.row = row;
        this.column = column;
        this.index = index;

        this.isSubSprite = true;

        this.pictureFile = pictureFile;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(image, (int) gameObject.transform.position.x, (int) gameObject.transform.position.y, width, height, null);
    }

    @Override
    public Component copy() {
        if (!isSubSprite) {
            return new Sprite(this.image, pictureFile);
        } else {
            return new Sprite(this.image, this.row, this.column, this.index, pictureFile);
        }
    }

    @Override
    public String serialize(int tabSize) {
        StringBuilder builder = new StringBuilder();

        builder.append(beginObjectProperty("Sprite", tabSize));
        builder.append(addBooleanProperty("isSubSprite", isSubSprite, tabSize + 1, true, true));

        if (isSubSprite) {
            builder.append(addStringProperty("FilePath", pictureFile, tabSize + 1, true, true));
            builder.append(addIntProperty("row", row, tabSize + 1, true, true));
            builder.append(addIntProperty("column", column, tabSize + 1, true, true));
            builder.append(addIntProperty("index", index, tabSize + 1, true, false));
            builder.append(closeObjectProperty(tabSize));

            return builder.toString();
        }

        builder.append(addStringProperty("FilePath", pictureFile, tabSize + 1, true, false));
        builder.append(closeObjectProperty(tabSize));
        return builder.toString();
    }

}
