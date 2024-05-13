package main.java.object.interact;

import java.awt.Color;

import main.java.object.interact.behavior.Boost;

public class JumpBooster extends Interactable {
    public JumpBooster(int x, int y) {
        super(x, y, Color.GREEN);
        behavior = new Boost();
        setSprite("./gfx/Objects/JumpBooster.png");
    }
}
