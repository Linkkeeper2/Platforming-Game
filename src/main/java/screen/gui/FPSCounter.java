package main.java.screen.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.TimerTask;

import main.java.MyGame;
import main.java.object.GameObject;

public class FPSCounter extends GameObject {
    private int fps;
    private int currFrames;
    private boolean running;
    private boolean update;

    public FPSCounter(int x, int y, Color color) {
        super(x, y, 0, 0, color);
    }

    public void draw(Graphics pen) {
        pen.setColor(color);
        pen.setFont(new Font("./gfx/Font/Peepo.ttf", 0, 20));
        pen.drawString("FPS: " + fps, x, y);
    }

    public void start() {
        running = true;
        update = true;
    }

    public void update() {
        if (!running)
            return;

        currFrames++;

        if (!update)
            return;

        MyGame.timer.schedule(new TimerTask() {
            public void run() {
                fps = currFrames;
                currFrames = 0;
                update = true;
            }
        }, 1000);
        update = false;
    }

    public void stop() {
        running = false;
    }
}
