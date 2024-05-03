package main.java.screen.gui.text;

import java.util.ArrayList;

import main.java.MyGame;
import main.java.screen.EditorScreen;
import main.java.screen.Screen;
import main.java.screen.gui.TextBox;

public class SaveThread extends Thread {
    private ArrayList<String> level;
    private TextBox box;

    public SaveThread(TextBox box, ArrayList<String> level) {
        this.level = level;
        this.box = box;
    }

    public void run() {
        while (!box.isSubmitted() && MyGame.screen instanceof EditorScreen && Screen.contains(box)) {
            System.out.print("");
        }

        if (!(MyGame.screen instanceof EditorScreen) || !Screen.contains(box))
            return;

        if (Screen.contains(EditorScreen.start) && Screen.contains(EditorScreen.end)) {
            MyGame.database.createLevel(box.getContents(), level);
            MyGame.status.addMessage("Level Saved!", 5000);
        } else
            MyGame.status.addMessage("Level must have a Start & End point.", 5000);

        Screen.remove(box);
    }
}
