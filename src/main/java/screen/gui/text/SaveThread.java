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
        while (!box.isSubmitted() && MyGame.screen instanceof EditorScreen) {
            System.out.print("");
        }

        if (!(MyGame.screen instanceof EditorScreen))
            return;

        MyGame.database.createLevel(box.getContents(), level);

        Screen.remove(box);
    }
}
