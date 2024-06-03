package main.java.screen.gui.text;

import java.util.ArrayList;

import main.java.MyGame;
import main.java.screen.EditorScreen;
import main.java.screen.Screen;

public class SaveThread extends TextThread {
    private ArrayList<ArrayList<String>> level;

    public SaveThread(TextBox box, ArrayList<ArrayList<String>> level) {
        super(box);
        this.level = level;
    }

    protected void action() {
        if (Screen.contains(EditorScreen.start) && Screen.contains(EditorScreen.end)) {
            MyGame.database.createLevel(box.getContents(), level);
            MyGame.status.addMessage("Level Saved!", 5000);
        } else
            MyGame.status.addMessage("Level must have a Start & End point.", 5000);
    }
}
