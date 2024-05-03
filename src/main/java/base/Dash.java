package main.java.base;

import java.awt.event.KeyEvent;

import main.java.object.entity.EntityBody;

public class Dash extends AbilityBase {
    private int speed;
    private int dashTime;
    private int currTime;
    private boolean running;

    public Dash(EntityBody entity, int speed, int dashTime) {
        super(entity, KeyEvent.VK_SHIFT);
        this.speed = speed;
        this.dashTime = dashTime;
    }

    public void action() {
        entity.x += speed * entity.direction;

        if (entity.isJumping())
            entity.y -= speed * entity.getGravity();
    }

    public void run() {
        if (running)
            return;

        running = true;

        while (currTime < dashTime) {
            action();
            currTime++;
        }

        currTime = 0;
        running = false;
    }
}
