package main.java.object.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import main.java.MyGame;
import main.java.object.GameObject;
import main.java.object.block.Collidable;
import main.java.object.meta.Hitbox;
import main.java.screen.Screen;

public abstract class EntityBody extends GameObject {
    protected boolean jumping, canJump;
    protected boolean alive;
    protected short xVel, yVel;
    protected short baseXVel, baseYVel;
    protected Hitbox hitbox;

    public EntityBody(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        xVel = 10;
        yVel = 10;
        baseXVel = xVel;
        baseYVel = yVel;
        hitbox = new Hitbox(x, y, width, height, Color.GREEN);
        // new EntityThread(this).start();
    }

    public void draw(Graphics pen) {
        super.draw(pen);
        hitbox.draw(pen);
    }

    public void update() {
        border();

        if (!canJump)
            hitbox.updateRect(x, y, width, height);
        else
            hitbox.updateRect(x, y - yVel, width, height);

        if (y >= MyGame.SCREEN_HEIGHT)
            kill();

        collisions();
    }

    private void border() {
        if (x < 0)
            x = 0;

        else if (x > MyGame.SCREEN_WIDTH - width - 16)
            x = MyGame.SCREEN_WIDTH - width - 16;
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

            hitbox.updateRect(x, y, width, height);
        }

        if (unflag)
            canJump = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public void kill() {
        alive = false;

        Screen.addThread(new ParticleGenerator(this, 15));

        xVel = baseXVel;
        yVel = baseYVel;
        jumping = false;
        canJump = false;
        alive = true;
    }
}
