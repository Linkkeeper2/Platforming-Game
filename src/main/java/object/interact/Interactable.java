package main.java.object.interact;

import java.awt.Color;
import java.awt.event.KeyEvent;

import main.java.object.GameObject;
import main.java.object.entity.Player;
import main.java.object.interact.behavior.InteractBehavior;

public abstract class Interactable extends GameObject {
    protected InteractBehavior behavior;
    protected boolean canActivate;

    public Interactable(int x, int y, Color color) {
        super(x, y, 64, 64, color);
        canActivate = true;
    }

    public void update() {
        if (Player.main != null && !isColliding(Player.main))
            canActivate = true;
    }

    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == 40 && isColliding(Player.main) && canActivate) {
            if (behavior != null)
                behavior.start();
            canActivate = false;
        }
    }
}
