package main.java.object.entity;

import main.java.screen.Screen;

public class ExternalJumpThread extends JumpThread {
    public ExternalJumpThread(EntityBody p) {
        super(p);
    }

    public void run() {
        double jumpheight = 20;

        while (jumpheight > 0 && entity.alive) {
            try {
                sleep(10);
            } catch (InterruptedException e) {
            }

            if (!Screen.subOn()) {
                entity.y -= (int) jumpheight * entity.gravity;
                jumpheight -= 0.5;
            }
        }

        entity.jumping = false;
    }
}
