package main.java.screen.gui.button;

import main.java.MyGame;
import main.java.screen.EditorScreen;

public class Editor implements ButtonAction {
    public void action() {
        MyGame.screen = new EditorScreen();
    }
}
