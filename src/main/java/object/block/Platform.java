package main.java.object.block;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import main.java.object.GameObject;

public class Platform extends GameObject {
    public static ArrayList<Platform> platforms = new ArrayList<>();
    private BlockType type;

    public Platform(int x, int y, int width, int height, Color color, BlockType type) {
        super(x, y, width, height, color);

        this.type = type;
        platforms.add(this);
    }

    public void draw(Graphics pen) {
        if (this.color != null) {
            pen.setColor(color);

            switch (type) {
                case SOLID:
                    pen.fillRect(x, y, width, height);
                    break;
            }
        }
    }

    public boolean[] getCollisions(GameObject g) {
        boolean[] collisions = new boolean[4];

        collisions[0] = getLeft(4).intersects(g.getRight(10));
        collisions[1] = getTop(4).intersects(g.getBottom(6));
        collisions[2] = getRight(4).intersects(g.getLeft(10));
        collisions[3] = getBottom(4).intersects(g.getTop(6));

        return collisions;
    }
}
