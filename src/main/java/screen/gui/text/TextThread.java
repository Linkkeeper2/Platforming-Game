package main.java.screen.gui.text;

import main.java.screen.Screen;
import main.java.screen.StartScreen;
import main.java.MyGame;

public abstract class TextThread extends Thread {
    protected TextBox box;

    public TextThread(TextBox box) {
        this.box = box;
        Screen.add(box);
    }

    public void run() {
        while (!box.isSubmitted() && !(MyGame.screen instanceof StartScreen) && Screen.contains(box)) {
            System.out.print("");
        }

        if (MyGame.screen instanceof StartScreen || !Screen.contains(box))
            return;

        action();

        Screen.remove(box);
    }

    protected abstract void action();

    public TextBox getBox() {
        return box;
    }
}
