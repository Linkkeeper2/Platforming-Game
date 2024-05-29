package main.java.screen.gui.text;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class PasswordBox extends TextBox {
    public PasswordBox(int x, int y, int width, String title) {
        super(x, y, width, title);
    }

    protected void drawTyped(Graphics pen) {
        pen.setFont(new Font("./gfx/Font/Peepo.ttf", 0, 20));

        pen.setColor(Color.BLACK);
        pen.drawString(title, x, y);
        pen.setColor(Color.WHITE);
        pen.drawString("*".repeat(contents.length()), x, y + 20);
    }
}
