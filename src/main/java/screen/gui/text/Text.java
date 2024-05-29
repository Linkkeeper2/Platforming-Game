package main.java.screen.gui.text;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.java.object.GameObject;

public class Text extends GameObject {
    private String contents;
    private int size;

    public Text(int x, int y, String contents, Color color, int size) {
        super(x, y, 0, 0, color);
        this.contents = contents;
        this.size = size;
    }

    public void draw(Graphics pen) {
        pen.setColor(color);
        pen.setFont(new Font("./gfx/Font/Peepo.ttf", 0, size));
        pen.drawString(contents, x, y);
    }

    public String getContents() {
        return contents;
    }
}
