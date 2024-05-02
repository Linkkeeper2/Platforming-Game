package main.java.screen.gui.button;

import main.java.MyGame;
import main.java.screen.GameScreen;
import main.java.screen.Screen;
import main.java.screen.StartScreen;

public class Quit implements ButtonAction {
    public void action() {
        if (MyGame.screen instanceof GameScreen) {
            MyGame.screen = new StartScreen();
            Screen.subscreen = null;
        } else
            System.exit(0);
    }
}
