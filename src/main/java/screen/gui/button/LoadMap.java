package main.java.screen.gui.button;

import main.java.MyGame;
import main.java.screen.Screen;
import main.java.screen.gui.text.LoadThread;
import main.java.screen.gui.text.TextBox;

public class LoadMap implements ButtonAction {
    public static int level;
    private TextBox box;

    public void action() {
        if (!Screen.contains(box))
            box = null;

        if (box == null) {
            box = new TextBox(650, MyGame.SCREEN_HEIGHT - 160, 250, "Level Number:");
            new LoadThread(box).start();
        } else {
            Screen.remove(box);
            box = null;
        }
    }
}
