package main.java.object.interact;

import java.awt.Color;

import main.java.object.interact.behavior.FlipBehavior;

public class GravityTile extends Interactable {
    public GravityTile(int x, int y) {
        super(x, y, Color.CYAN);
        behavior = new FlipBehavior();
        setSprite("./gfx/Objects/GravityTile.png");
    }
}
