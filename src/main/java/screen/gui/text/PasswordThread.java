package main.java.screen.gui.text;

import main.java.screen.Screen;

public class PasswordThread extends TextThread {
    public PasswordThread(int x, int y, int width) {
        super(new PasswordBox(x, y, width, "Enter Your Password:"));
    }

    protected void action() {
        Screen.remove(box);
    }
}
