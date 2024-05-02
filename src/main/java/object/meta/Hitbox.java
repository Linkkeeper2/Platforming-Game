package main.java.object.meta;

import java.awt.Color;
import java.awt.Graphics;

import main.java.object.GameObject;
import main.java.screen.Screen;

public class Hitbox extends GameObject {
    public Hitbox(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    public void draw(Graphics pen) {
        if (Screen.debugMode) {
            pen.setColor(color);
            pen.drawRect(x, y, width, height);
        }
    }

    public void updateRect(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
