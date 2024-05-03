package main.java.screen.gui.button;

import main.java.MyGame;
import main.java.screen.RoomScreen;
import main.java.server.Account;
import main.java.server.ServerUpdates;

public class Start implements ButtonAction {
    public void action() {
        MyGame.database.refreshLevels();
        MyGame.screen = new RoomScreen(0);
        MyGame.database.addPlayer(Account.name);
        new ServerUpdates().start();
    }
}
