package main.java.screen.gui.button;

import main.java.MyGame;
import main.java.screen.RoomScreen;

public class Start implements ButtonAction {
    public void action() {
        MyGame.database.refreshLevels();
        MyGame.screen = new RoomScreen(0);
    }
}
