package main.java.screen.gui;

import main.java.MyGame;
import main.java.screen.RoomScreen;

public class Start implements ButtonAction {
    public void action() {
        MyGame.screen = new RoomScreen(0);
    }
}
