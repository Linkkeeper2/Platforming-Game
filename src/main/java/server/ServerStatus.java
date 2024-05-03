package main.java.server;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.TimerTask;

import main.java.MyGame;

public class ServerStatus {
    private ArrayList<String> messages;

    public ServerStatus() {
        messages = new ArrayList<>();
    }

    public void draw(Graphics pen) {
        pen.setFont(new Font("./gfx/Font/Peepo.ttf", 0, 20));
        pen.setColor(Color.BLACK);

        for (int i = 0; i < messages.size(); i++)
            pen.drawString(messages.get(i), 8, 48 + i * 48);
    }

    public void addMessage(String message) {
        messages.add(message);

        MyGame.timer.schedule(new TimerTask() {
            public void run() {
                messages.remove(message);
            }
        }, 2500);
    }

    public void addMessage(String message, int timeAlive) {
        messages.add(message);

        MyGame.timer.schedule(new TimerTask() {
            public void run() {
                messages.remove(message);
            }
        }, timeAlive);
    }
}
