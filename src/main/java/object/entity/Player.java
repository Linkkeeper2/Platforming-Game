package main.java.object.entity;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import main.java.object.GameObject;
import main.java.object.block.Platform;

public class Player extends GameObject {
    private boolean[] controls;
    private short xVel, yVel;
    protected boolean jumping, canJump;

    public Player(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        controls = new boolean[3];
        xVel = 10;
        yVel = 5;
    }

    public void update() {
        y += yVel;

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
        ArrayList<Platform> platforms = Platform.platforms;

        boolean unflag = true;

        for (int i = 0; i < platforms.size(); i++) {
            Platform platform = platforms.get(i);

            boolean[] intersections = platform.getCollisions(this);

            if (intersections[0])
                this.x = platform.x - this.width;

            if (intersections[1]) {
                this.y = platform.y - this.height;
                canJump = true;
                unflag = false;
            }

            if (intersections[2])
                this.x = platform.x + platform.width;

            if (intersections[3]) {
                this.y = platform.y + platform.height;
                jumping = false;
            }
        }

        if (unflag)
            canJump = false;
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
                break;

            case 39: // RIGHT
                controls[2] = false;
                break;
        }
    }
}
