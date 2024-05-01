package main.java.screen.gui;

import java.awt.Color;
import java.awt.Graphics;

import main.java.object.GameObject;

public class Grid extends GameObject {
    private int rows, cols;

    public Grid(int x, int y, int width, int height, int rows, int cols) {
        super(x, y, width, height, Color.BLACK);
        this.rows = rows;
        this.cols = cols;
    }

    public void update() {
    }

    public void draw(Graphics pen) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                pen.setColor(this.color);
                pen.drawRect(c * width, r * height, width, height);
            }
        }
    }
}
