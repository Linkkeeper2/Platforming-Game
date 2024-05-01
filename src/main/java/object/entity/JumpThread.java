package main.java.object.entity;

public class JumpThread extends Thread {
    private Player player;

    public JumpThread(Player p) {
        this.player = p;
    }

    public void run() {
        double jumpheight = 20;

        while (jumpheight > 0 && player.jumping) {
            try {
                sleep(10);
            } catch (InterruptedException e) {
            }

            player.y -= (int) jumpheight;
            jumpheight -= 0.5;
        }

        player.jumping = false;
    }
}
