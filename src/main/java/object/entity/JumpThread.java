package main.java.object.entity;

import main.java.MyGame;
import main.java.screen.Screen;

public class JumpThread extends Thread {
    protected EntityBody entity;

    public JumpThread(EntityBody p) {
        this.entity = p;
    }

    public void run() {
        double jumpheight = 15.5;

        while (entity.jumping && jumpheight > 0 && entity.alive) {
            try {
                sleep(10);
            } catch (InterruptedException e) {
            }

            if (!Screen.subOn()) {
                entity.y -= (int) jumpheight * 100 / MyGame.fps.getFPS() * entity.gravity;
                jumpheight -= 0.5;
            }
        }

        entity.jumping = false;
    }
}
