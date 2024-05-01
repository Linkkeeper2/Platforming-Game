package main.java.screen.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import main.java.object.GameObject;

public class TextBox extends GameObject {
    private String title;
    private String contents;
    private boolean submitted;

    public TextBox(int x, int y, int width, String title) {
        super(x, y, width, 25, Color.GRAY);
        this.title = title;
        this.contents = "";
    }

    public void draw(Graphics pen) {
        super.draw(pen);

        pen.setFont(new Font("./gfx/Font/Peepo.ttf", 0, 20));

        Graphics2D g2d = (Graphics2D) pen;
        FontMetrics fm = g2d.getFontMetrics();

        int sum = 0;

        for (int i = 0; i < contents.length(); i++)
            sum += fm.charWidth(contents.charAt(i));

        if (sum > 250)
            width = sum;

        else
            width = 250;

        pen.setColor(Color.BLACK);
        pen.drawString(title, x, y);
        pen.setColor(Color.WHITE);
        pen.drawString(contents, x, y + 20);
    }

    @SuppressWarnings("static-access")
    public void keyPressed(KeyEvent ke) {
        super.keyPressed(ke);

        ArrayList<Integer> excludes = new ArrayList<>();
        excludes.add(ke.VK_SHIFT);
        excludes.add(ke.VK_CONTROL);
        excludes.add(ke.VK_ALT);
        excludes.add(ke.VK_WINDOWS);
        excludes.add(ke.VK_CAPS_LOCK);
        excludes.add(ke.VK_TAB);
        excludes.add(ke.VK_NUM_LOCK);

        if (ke.getKeyCode() == ke.VK_ENTER)
            submitted = true;

        else if (ke.getKeyCode() == ke.VK_BACK_SPACE) {
            if (contents.length() > 0)
                backspace();
        }

        else if (ke.getKeyCode() == ke.VK_SPACE)
            contents += " ";

        else {
            if (!excludes.contains(ke.getKeyCode()))
                contents += ke.getKeyChar() + "";
        }
    }

    private void backspace() {
        contents = contents.substring(0, contents.length() - 1);
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public String getContents() {
        return contents;
    }
}
