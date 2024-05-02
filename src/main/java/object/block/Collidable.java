package main.java.object.block;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import main.java.object.GameObject;
import main.java.object.meta.Hitbox;
import main.java.screen.Screen;

public abstract class Collidable extends GameObject {
    public static ArrayList<Collidable> collidables = new ArrayList<>();
    protected Hitbox hitbox;

    public Collidable(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);

        collidables.add(this);
        hitbox = new Hitbox(x, y, width, height, Color.BLUE);
    }

    public void draw(Graphics pen) {
        super.draw(pen);

        if (Screen.debugMode) {
            hitbox.draw(pen);
        }
    }

    public boolean[] getCollisions(GameObject g) {
        boolean[] collisions = new boolean[4];

        return collisions;
    }

    public void collideAction(GameObject g) {
    }
}
