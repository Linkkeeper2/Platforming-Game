package main.java.screen;

import java.awt.Color;

import main.java.MyGame;
import main.java.screen.gui.Button;
import main.java.screen.gui.button.AccountAction;
import main.java.screen.gui.button.BackToMenu;
import main.java.screen.gui.text.Text;
import main.java.server.Account;

public class AccountScreen extends Screen {
    public AccountScreen() {
        objects.add(new Button(10, MyGame.SCREEN_HEIGHT - 100, 150, 50, Color.GRAY, "Back to Menu", new BackToMenu()));
        objects.add(new Button(MyGame.SCREEN_WIDTH - 175, MyGame.SCREEN_HEIGHT - 100, 150, 50, Color.GRAY, "Register",
                new AccountAction(true)));
        objects.add(new Button(MyGame.SCREEN_WIDTH - 175, MyGame.SCREEN_HEIGHT - 175, 150, 50, Color.GRAY, "Login",
                new AccountAction(false)));
        objects.add(new Text(MyGame.SCREEN_WIDTH - 175, MyGame.SCREEN_HEIGHT - 200, Account.name, Color.BLACK, 24));
    }
}
