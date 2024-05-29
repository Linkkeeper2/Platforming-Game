package main.java.screen.gui.text;

import main.java.MyGame;
import main.java.screen.EditorScreen;
import main.java.screen.RoomScreen;
import main.java.screen.Screen;

public class LoadThread extends TextThread {
    private int level;

    public LoadThread(TextBox box) {
        super(box);
    }

    protected void action() {
        try {
            MyGame.screen = new EditorScreen();

            level = Integer.parseInt(box.getContents());

            RoomScreen.loadRoom(level);

            MyGame.status.addMessage("Map, " + RoomScreen.getRoomName() + ", loaded successfully!", 5000);
        } catch (NumberFormatException e) {
            MyGame.status.addMessage("Could not load map.", 5000);
            Screen.remove(box);
            box = null;
        }
    }
}
