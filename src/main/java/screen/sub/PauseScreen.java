package main.java.screen.sub;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import linkk.util.StringUtil;
import main.java.MyGame;
import main.java.screen.gui.Button;
import main.java.screen.gui.Overlay;
import main.java.screen.gui.button.Quit;
import main.java.screen.gui.button.Restart;
import main.java.screen.gui.button.Resume;

public class PauseScreen extends SubScreen {
    public PauseScreen() {
        objects.add(new Overlay(0, 0, MyGame.SCREEN_WIDTH, MyGame.SCREEN_HEIGHT, new Color(0, 0, 0, 128)));
        objects.add(new Button(MyGame.SCREEN_WIDTH / 2 - 75, 200, 150, 50, Color.GRAY, "Resume", new Resume()));
        objects.add(new Button(MyGame.SCREEN_WIDTH / 2 - 75, 275, 150, 50, Color.GRAY, "Restart", new Restart()));
        objects.add(new Button(MyGame.SCREEN_WIDTH / 2 - 75, 350, 150, 50, Color.GRAY, "Quit", new Quit()));
    }

    public void draw(Graphics pen) {
        super.draw(pen);
        pen.setColor(Color.WHITE);
        pen.setFont(new Font("./gfx/Font/Peepo.ttf", 0, 20));
        pen.drawString("Paused", MyGame.SCREEN_WIDTH / 2 - StringUtil.getWidth(pen, "Paused") / 2, 150);
    }
}
