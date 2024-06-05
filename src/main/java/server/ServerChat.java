package main.java.server;

import java.awt.Color;
import java.awt.Graphics;

import java.util.ArrayList;

import main.java.screen.gui.text.Text;

public class ServerChat {
    private ArrayList<Text> messages;
    private Text tip;
    public int left, bottom;

    public ServerChat() {
        messages = new ArrayList<>();
        tip = new Text(left, bottom, "/ to chat", Color.BLACK, 24);
    }

    public void draw(Graphics pen) {
        for (int i = 0; i < messages.size(); i++) {
            Text message = messages.get(i);

            message.x = left + 8;
            message.y = bottom - (messages.size() - i) * 28;

            if (message != null)
                message.draw(pen);
        }

        tip.x = left + 8;
        tip.y = bottom;
        tip.draw(pen);
    }

    public void clearMessages() {
        messages = new ArrayList<>();
    }

    public void addMessage(Text t) {
        t.x = left;
        t.y = bottom;
        messages.add(t);
    }

    public int getSize() {
        return messages.size();
    }
}
