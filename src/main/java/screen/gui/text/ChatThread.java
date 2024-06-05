package main.java.screen.gui.text;

import main.java.MyGame;
import main.java.screen.sub.ChatScreen;

public class ChatThread extends TextThread {
    public ChatThread(TextBox box) {
        super(box);
    }

    public void action() {
        MyGame.database.addMessage(box.getContents());
        ChatScreen.stopTyping();
    }
}
