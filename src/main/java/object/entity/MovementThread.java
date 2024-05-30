package main.java.object.entity;

import main.java.screen.Screen;

public class MovementThread extends Thread {
    private Player player;

    public MovementThread(Player player) {
        this.player = player;
    }

    public void run() {
        while (Screen.contains(player)) {
            player.controls();
            try {
                sleep(10);
            } catch (InterruptedException e) {
            }
        }
    }
}
