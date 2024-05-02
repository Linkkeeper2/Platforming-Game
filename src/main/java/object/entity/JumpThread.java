package main.java.object.entity;

public class JumpThread extends Thread {
    private EntityBody entity;

    public JumpThread(EntityBody p) {
        this.entity = p;
    }

    public void run() {
        double jumpheight = 20;

        while (jumpheight > 0 && entity.jumping) {
            try {
                sleep(10);
            } catch (InterruptedException e) {
            }

            entity.y -= (int) jumpheight;
            jumpheight -= 0.5;
        }

        entity.jumping = false;
    }
}
