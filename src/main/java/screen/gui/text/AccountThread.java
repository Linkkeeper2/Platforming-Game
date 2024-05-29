package main.java.screen.gui.text;

import main.java.MyGame;
import main.java.screen.AccountScreen;
import main.java.screen.Screen;

public class AccountThread extends Thread {
    private boolean doRegister;

    public AccountThread(boolean doRegister) {
        this.doRegister = doRegister;
    }

    public void run() {
        if (doRegister)
            register();
        else
            login();

        MyGame.screen = new AccountScreen();
    }

    private void register() {
        UsernameThread userThread = new UsernameThread(
                new TextBox(MyGame.SCREEN_WIDTH / 2 - 75, MyGame.SCREEN_HEIGHT - 150, 150, "Enter a Username:"));

        while (!userThread.getBox().isSubmitted())
            System.out.print("");

        String username = userThread.getBox().getContents();

        Screen.remove(userThread.getBox());

        if (MyGame.database.userExists(username)) {
            MyGame.status.addMessage("'" + username + "' is taken.", 5000);
            return;
        }

        PasswordThread passThread = new PasswordThread(MyGame.SCREEN_WIDTH / 2 - 75, MyGame.SCREEN_HEIGHT - 150, 150);

        while (!passThread.getBox().isSubmitted())
            System.out.print("");

        String password = passThread.getBox().getContents();

        Screen.remove(passThread.getBox());

        MyGame.database.registerAccount(username, password);
    }

    private void login() {
        UsernameThread userThread = new UsernameThread(
                new TextBox(MyGame.SCREEN_WIDTH / 2 - 75, MyGame.SCREEN_HEIGHT - 150, 150, "Enter your Username:"));

        while (!userThread.box.isSubmitted())
            System.out.print("");

        String username = userThread.box.getContents();

        Screen.remove(userThread.getBox());

        if (!MyGame.database.userExists(username)) {
            MyGame.status.addMessage("'" + username + "' does not exist.", 5000);
            return;
        }

        PasswordThread passThread = new PasswordThread(MyGame.SCREEN_WIDTH / 2 - 75, MyGame.SCREEN_HEIGHT - 150, 150);

        while (!passThread.box.isSubmitted())
            System.out.print("");

        String password = passThread.box.getContents();

        Screen.remove(passThread.getBox());

        MyGame.database.login(username, password);
    }
}
