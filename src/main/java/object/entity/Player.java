package main.java.object.entity;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import main.java.MyGame;
import main.java.object.GameObject;
import main.java.object.block.Collidable;
import main.java.screen.RoomScreen;
import main.java.screen.Screen;

public class Player extends GameObject {
    private boolean[] controls;
    private short xVel, yVel;
    protected boolean jumping, canJump;
    public static Player main;
    private boolean alive;

    public Player(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        controls = new boolean[3];
        xVel = 10;
        yVel = 10;
        main = this;
        alive = true;
    }

    public void update() {
        y += yVel;

        if (y >= MyGame.SCREEN_HEIGHT) {
            x = 0;
            y = 0;
        }

        controls();
        collisions();
    }

    private void controls() {
        if (controls[0] || controls[2])
            x += xVel * direction;

        if (controls[1] && !jumping && canJump) {
            jumping = true;
            canJump = false;
            new JumpThread(this).start();
        }
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

        xVel = 10;
        yVel = 10;
        RoomScreen.resetPlayer();
        jumping = false;
        canJump = false;
        alive = true;
    }

    public void keyPressed(KeyEvent ke) {
        switch (ke.getKeyCode()) {
            case 37: // LEFT
                controls[0] = true;
                direction = -1;
                break;

            case 38: // UP
                controls[1] = true;
                break;

            case 39: // RIGHT
                controls[2] = true;
                direction = 1;
                break;
        }
    }

    public void keyReleased(KeyEvent ke) {
        switch (ke.getKeyCode()) {
            case 37: // LEFT
                controls[0] = false;
                break;

            case 38: // UP
                controls[1] = false;
                jumping = false;
                break;

            case 39: // RIGHT
                controls[2] = false;
                break;
        }
    }
}
