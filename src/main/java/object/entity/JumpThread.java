package main.java.object.entity;

import main.java.MyGame;
import main.java.screen.Screen;

public class JumpThread extends Thread {
    private EntityBody entity;

    public JumpThread(EntityBody p) {
        this.entity = p;
    }

    public void run() {
        double jumpheight = 20;

        while (entity.jumping && jumpheight > 0) {
            try {
                sleep(10);
            } catch (InterruptedException e) {
            }

            if (!Screen.subOn()) {
                entity.y -= (int) jumpheight * 65 / MyGame.fps.getFPS() * entity.gravity;
                jumpheight -= 0.5;
            }
        }

        entity.jumping = false;
    }
}
