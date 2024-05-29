package main.java.screen.gui.text;

import main.java.screen.Screen;

public class UsernameThread extends TextThread {
    public UsernameThread(TextBox box) {
        super(box);
    }

    protected void action() {
        Screen.remove(box);
    }
}
