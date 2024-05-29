package main.java.screen.gui.button;

import main.java.MyGame;
import main.java.screen.AccountScreen;

public class AccountMenu implements ButtonAction {
    public void action() {
        MyGame.screen = new AccountScreen();
    }
}
