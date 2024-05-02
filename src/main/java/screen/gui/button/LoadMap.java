package main.java.screen.gui.button;

import main.java.MyGame;
import main.java.screen.Screen;
import main.java.screen.gui.TextBox;
import main.java.screen.gui.text.LoadThread;

public class LoadMap implements ButtonAction {
    public static int level;

    public void action() {
        TextBox box = new TextBox(650, MyGame.SCREEN_HEIGHT - 160, 250, "Level Number:");
        Screen.add(box);
        new LoadThread(box).start();
    }
}
