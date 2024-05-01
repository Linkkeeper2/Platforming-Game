package main.java.object.block;

import java.awt.Color;
import java.util.ArrayList;

import main.java.object.GameObject;

public abstract class Collidable extends GameObject {
    public static ArrayList<Collidable> collidables = new ArrayList<>();

    public Collidable(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);

        collidables.add(this);
    }

    public boolean[] getCollisions(GameObject g) {
        boolean[] collisions = new boolean[4];

        return collisions;
    }

    public void collideAction(GameObject g) {
    }
}
