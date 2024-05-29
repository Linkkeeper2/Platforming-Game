package main.java.screen.gui.button;

import main.java.screen.gui.text.AccountThread;

public class AccountAction implements ButtonAction {
    private boolean doRegister;

    public AccountAction(boolean doRegister) {
        this.doRegister = doRegister;
    }

    public void action() {
        new AccountThread(doRegister).start();
    }
}
