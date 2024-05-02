package main.java.screen.gui.button;

import main.java.object.entity.Player;
import main.java.screen.Screen;

public class Restart implements ButtonAction {
    public void action() {
        Player.main.kill();
        Screen.subscreen = null;
    }
}
