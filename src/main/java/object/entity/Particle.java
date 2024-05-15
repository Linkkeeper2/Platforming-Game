package main.java.object.entity;

import java.awt.Color;

import main.java.object.GameObject;

public class Particle extends GameObject {
    private double xVel, yVel;

    public Particle(int x, int y, Color color, byte direction) {
        int size = (int) (Math.random() * 8) + 8;

        super(x, y, size, size, color);

        xVel = (double) (Math.random() * 16) + 8;

        if (direction == 0)
            direction = -1;

        this.direction = direction;

        yVel = (double) (Math.random() * 8) + 8;
        new ParticleThread(this).start();
    }

    public void update() {
        super.update();

        x += (int) xVel * direction;
        y -= yVel;

        yVel -= 1;
        hitbox.updateRect(x, y, width, height);
    }
}
