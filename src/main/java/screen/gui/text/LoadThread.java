package main.java.screen.gui.text;

import main.java.MyGame;
import main.java.screen.EditorScreen;
import main.java.screen.RoomScreen;
import main.java.screen.Screen;
import main.java.screen.gui.TextBox;

public class LoadThread extends Thread {
    private int level;
    private TextBox box;

    public LoadThread(TextBox box) {
        this.box = box;
    }

    public void run() {
        while (!box.isSubmitted() && MyGame.screen instanceof EditorScreen && Screen.contains(box)) {
            System.out.print("");
        }

        if (!(MyGame.screen instanceof EditorScreen) || !Screen.contains(box))
            return;

        try {
            MyGame.screen = new EditorScreen();

            level = Integer.parseInt(box.getContents());

            RoomScreen.loadRoom(level);

            MyGame.status.addMessage("Map loaded successfully!", 5000);

            Screen.remove(box);
        } catch (NumberFormatException e) {
            MyGame.status.addMessage("Could not load map.", 5000);
            Screen.remove(box);
            box = null;
        }
    }
}
