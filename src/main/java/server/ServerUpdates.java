package main.java.server;

import main.java.MyGame;
import main.java.object.entity.Player;
import main.java.screen.GameScreen;

public class ServerUpdates extends Thread {
    public void run() {
        while (MyGame.screen instanceof GameScreen) {
            MyGame.database.updateMain();
            Player.updatePlayers();
            MyGame.database.addPlayers();
            try {
                sleep(10);
            } catch (InterruptedException e) {
            }
        }
    }
}
