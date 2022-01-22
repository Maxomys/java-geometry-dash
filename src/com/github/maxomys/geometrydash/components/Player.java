package com.github.maxomys.geometrydash.components;

import com.github.maxomys.geometrydash.jade.Component;
import com.github.maxomys.geometrydash.util.Constants;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Player extends Component {

    Sprite layerOne, layerTwo, layerThree;
    public int width, height;

    public Player(Sprite layerOne, Sprite layerTwo, Sprite layerThree, Color colorOne, Color colorTwo) {
        this.layerOne = layerOne;
        this.layerTwo = layerTwo;
        this.layerThree = layerThree;

        this.width = Constants.PLAYER_WIDTH;
        this.height = Constants.PLAYER_HEIGHT;

        int threshold = 200;

        for (int x = 0; x < layerOne.image.getWidth(); x++) {
            for (int y = 0; y < layerOne.image.getHeight(); y++) {
                Color color = new Color(layerOne.image.getRGB(x, y));
                if (color.getRed() > threshold  && color.getGreen() > threshold && color.getBlue() > threshold) {
                    layerOne.image.setRGB(x, y, colorOne.getRGB());
                }
            }
        }
        for (int x = 0; x < layerTwo.image.getWidth(); x++) {
            for (int y = 0; y < layerTwo.image.getHeight(); y++) {
                Color color = new Color(layerTwo.image.getRGB(x, y));
                if (color.getRed() > threshold  && color.getGreen() > threshold && color.getBlue() > threshold) {
                    layerTwo.image.setRGB(x, y, colorTwo.getRGB());
                }
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        AffineTransform transform = new AffineTransform();
        transform.setToIdentity();
        transform.translate(gameObject.transform.position.x, gameObject.transform.position.y);
        transform.rotate(gameObject.transform.rotation, width * gameObject.transform.scale.x / 2.0,
                height * gameObject.transform.scale.y / 2.0);
        transform.scale(gameObject.transform.scale.x, gameObject.transform.scale.y);

        g2.drawImage(layerOne.image, transform, null);
        g2.drawImage(layerTwo.image, transform, null);
        g2.drawImage(layerThree.image, transform, null);
    }

    @Override
    public Component copy() {
        return null;
    }

}
