package main.java.object.interact;

import java.awt.Color;

import main.java.object.GameObject;
import main.java.object.entity.Player;
import main.java.object.interact.behavior.InteractBehavior;

public abstract class Interactable extends GameObject {
    protected InteractBehavior behavior;
    protected boolean canActivate;
    public static double animationCycle = 0;
    public static double animationCycleSpeed = 0.3;

    public Interactable(int x, int y, Color color) {
        super(x, y, 64, 64, color);
        canActivate = true;
    }

    protected void spriteAnimation() {
        if (animated) {
            try {
                sprite = spriteSheet.getSprite((int) animationCycle);
            } catch (IndexOutOfBoundsException e) {
                animationCycle = 0;
            }
        }
    }

    public void update() {
        super.update();

        if (Player.main != null && !isColliding(Player.main))
            canActivate = true;

        if (canActivate && Player.main != null && isColliding(Player.main)) {
            if (behavior != null)
                behavior.start();

            canActivate = false;
        }
    }
}
