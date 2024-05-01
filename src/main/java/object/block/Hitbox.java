package main.java.object.block;

import java.awt.Color;
import java.awt.Graphics;

import main.java.object.GameObject;

public class Hitbox extends GameObject {
    public Hitbox(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    public void draw(Graphics pen) {
        pen.setColor(color);
        pen.drawRect(x, y, width, height);
    }
}
