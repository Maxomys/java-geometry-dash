package com.github.maxomys.geometrydash.jade;

import com.github.maxomys.geometrydash.util.Time;
import com.github.maxomys.geometrydash.util.Constants;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame implements Runnable {

    public ML mouseListener;
    public KL keyListener;
    public boolean isInEditor = true;

    private static Window window = null;
    private boolean isRunning = true;
    private Scene currentScene = null;
    private Image doubleBufferImage = null;
    private Graphics doubleBufferGraphics = null;

    private Window() {
        this.mouseListener = new ML();
        this.keyListener = new KL();

        this.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        this.setTitle(Constants.SCREEN_TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.addKeyListener(keyListener);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
        this.setLocationRelativeTo(null);
    }

    public void init() {
        changeScene(0);
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void changeScene(int scene) {
        switch (scene) {
            case 0:
                isInEditor = true;
                currentScene = new LevelEditorScene("Level Editor");
                currentScene.init();
                break;
            case 1:
                isInEditor = false;
                currentScene = new LevelScene("Level");
                currentScene.init();
                break;
            default:
                System.out.println("Do not know the scene");
                currentScene = null;
                break;
        }
    }

    public static Window getInstance() {
        if (Window.window == null) {
            Window.window = new Window();
        }

        return Window.window;
    }

    public void update(double dt) {
        currentScene.update(dt);
        draw(getGraphics());
    }

    public void draw(Graphics g) {
        if (doubleBufferImage == null) {
            doubleBufferImage = createImage(getWidth(), getHeight());
            doubleBufferGraphics = doubleBufferImage.getGraphics();
        }

        renderOffScreen(doubleBufferGraphics);

        g.drawImage(doubleBufferImage, 0, 0, getWidth(), getHeight(), null);
    }

    private void renderOffScreen(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        currentScene.draw(g2);
    }

    @Override
    public void run() {
        double lastFrameTime = 0.0;

        try {
            while (isRunning) {
                double time = Time.getTime();
                double deltaTime = time - lastFrameTime;
                lastFrameTime = time;

                update(deltaTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
