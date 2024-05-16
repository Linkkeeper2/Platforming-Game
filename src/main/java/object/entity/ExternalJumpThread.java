package main.java.object.entity;

import main.java.MyGame;
import main.java.screen.Screen;

public class ExternalJumpThread extends JumpThread {
    public ExternalJumpThread(EntityBody p) {
        super(p);
    }

    public void run() {
        double jumpheight = 15.5;

        while (jumpheight > 0) {
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
