package main.java.object.block;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import main.java.object.GameObject;
import main.java.object.meta.Hitbox;
import main.java.screen.Screen;

public abstract class Collidable extends GameObject {
    public static ArrayList<Collidable> collidables = new ArrayList<>();
    /**
     * Enabled sides of the hitboxes on the collidable.
     * 0: Left
     * 1: Top
     * 2: Right
     * 3: Bottom
     */
    protected boolean[] enabled;

    public Collidable(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);

        collidables.add(this);
        hitbox = new Hitbox(x, y, width, height, Color.BLUE);
        enabled = new boolean[] { true, true, true, true };

        if (!(this instanceof MovingPlatform))
            setEnabled();
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

    public void setEnabled() {
        boolean[] adjacent = Screen.getAdjacent(this);

        for (int i = 0; i < adjacent.length; i++) {
            enabled[i] = !adjacent[i];
        }
    }
}
