package main.java.object.entity;

import java.awt.Color;
import java.util.ArrayList;

import main.java.MyGame;
import main.java.object.GameObject;
import main.java.object.block.Collidable;
import main.java.screen.Screen;

public abstract class EntityBody extends GameObject {
    protected boolean jumping, canJump;
    protected boolean alive;
    protected short xVel, yVel;
    protected short baseXVel, baseYVel;

    public EntityBody(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        xVel = 10;
        yVel = 10;
        baseXVel = xVel;
        baseYVel = yVel;
    }

    public void update() {
        if (y >= MyGame.SCREEN_HEIGHT)
            kill();

        collisions();
    }

    private void collisions() {
        ArrayList<Collidable> collidables = Collidable.collidables;

        boolean unflag = true;

        for (int i = 0; i < collidables.size(); i++) {
            Collidable platform = collidables.get(i);

            boolean[] intersections = platform.getCollisions(this);

            platform.collideAction(this);

            boolean side = true;

            if (intersections[1]) {
                this.y = platform.y - this.height;
                canJump = true;
                unflag = false;
                side = false;
            }

            if (intersections[0] && side)
                this.x = platform.x - this.width;

            if (intersections[2] && side)
                this.x = platform.x + platform.width;

            if (intersections[3]) {
                this.y = platform.y + platform.height;
                jumping = false;
                side = false;
            }
        }

        if (unflag)
            canJump = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public void kill() {
        alive = false;

        for (int i = 0; i < 10; i++)
            Screen.add(new Particle(x + width / 2, y + height / 2, color, (byte) (i % 2)));

        xVel = baseXVel;
        yVel = baseYVel;
        jumping = false;
        canJump = false;
        alive = true;
    }
}
