package main.java.screen.gui.button;

import main.java.screen.Screen;

public class Resume implements ButtonAction {
    public void action() {
        Screen.subscreen = null;
    }
}
