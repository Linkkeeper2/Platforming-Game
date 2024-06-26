package main.java.screen.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import linkk.util.StringUtil;
import main.java.object.GameObject;
import main.java.screen.gui.button.ButtonAction;

public class Button extends GameObject {
    private ButtonAction action;
    private String content;

    public Button(int x, int y, int width, int height, Color color, String content, ButtonAction action) {
        super(x, y, width, height, color);
        this.action = action;
        this.content = content;
    }

    public Button(int x, int y, int width, int height, Color color, String content, String spritePath,
            ButtonAction action) {
        super(x, y, width, height, color);
        this.action = action;
        this.content = content;
        setSprite(spritePath);
    }

    public void draw(Graphics pen) {
        pen.setColor(this.color);
        pen.fillRect(x, y, width, height);

        pen.setColor(Color.WHITE);
        pen.setFont(new Font("gfx/Font/peepo.ttf", 0, 20));

        Graphics2D g2d = (Graphics2D) pen;
        FontMetrics fm = g2d.getFontMetrics();

        int x = getRect().x + ((int) getRect().getWidth() - (int) getRect().getWidth() / 2)
                - StringUtil.getWidth(pen, content) / 2;
        int y = getRect().y + ((int) getRect().getHeight() - (int) getRect().getHeight() / 2) + fm.getAscent() / 2 - 4;

        if (this.sprite == null)
            g2d.drawString(content, x, y);
        else
            pen.drawImage(sprite, this.x + width / 2 - sprite.getWidth() / 2,
                    this.y + height / 2 - sprite.getHeight() / 2, null);
    }

    public void mousePressed(MouseEvent me) {
        int x = me.getX() - 8;
        int y = me.getY() - 32;

        if (getRect().contains(x, y) && me.getButton() == 1)
            action.action();
    }

    public void mouseDragged(MouseEvent me) {
        int x = me.getX() - 8;
        int y = me.getY() - 32;

        hover(x, y);
    }

    public void mouseMoved(MouseEvent me) {
        int x = me.getX() - 8;
        int y = me.getY() - 32;

        hover(x, y);
    }

    private void hover(int x, int y) {
        if (getRect().intersects(new Rectangle(x, y, 1, 1)))
            this.color = getDarkColor(25);

        else
            this.color = this.normalColor;
    }
}
