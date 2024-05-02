package main.java.object.entity;

import java.awt.Color;
import java.awt.event.KeyEvent;

import main.java.screen.RoomScreen;
import main.java.screen.Screen;

public class Player extends EntityBody {
    private boolean[] controls;
    public static Player main;

    public Player(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        controls = new boolean[3];
        controls[0] = Screen.globalControls[0];
        controls[2] = Screen.globalControls[1];

        if (controls[0])
            direction = -1;

        if (controls[2])
            direction = 1;

        main = this;
        alive = true;
    }

    public void update() {
        y += yVel;

        controls();
        super.update();
    }

    private void controls() {
        if (controls[0] || controls[2]) {
            x += xVel * direction;
            hitbox.updateRect(x, y, width, height);
        }

        if (controls[1] && !jumping && canJump) {
            jumping = true;
            canJump = false;
            new JumpThread(this).start();
        }
    }

    public void kill() {
        super.kill();
        RoomScreen.resetPlayer();
    }

    public void keyPressed(KeyEvent ke) {
        switch (ke.getKeyCode()) {
            case 37: // LEFT
                controls[0] = true;
                Screen.globalControls[0] = true;
                direction = -1;
                break;

            case 38: // UP
                controls[1] = true;
                break;

            case 39: // RIGHT
                controls[2] = true;
                Screen.globalControls[1] = true;
                direction = 1;
                break;
        }
    }

    public void keyReleased(KeyEvent ke) {
        switch (ke.getKeyCode()) {
            case 37: // LEFT
                controls[0] = false;
                Screen.globalControls[0] = false;
                break;

            case 38: // UP
                controls[1] = false;
                jumping = false;
                break;

            case 39: // RIGHT
                controls[2] = false;
                Screen.globalControls[1] = true;
                break;
        }
    }
}
