package com.github.maxomys.geometrydash.main;

import com.github.maxomys.geometrydash.jade.Window;

public class Main {

    public static void main(String... args) {
        Window window = Window.getInstance();
        window.init();

        Thread mainThread = new Thread(window);
        mainThread.start();
    }

}
