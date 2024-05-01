package main.java.screen.gui.button;

import main.java.MyGame;
import main.java.screen.StartScreen;

public class BackToMenu implements ButtonAction {
    public void action() {
        MyGame.screen = new StartScreen();
    }
}
