package main.java.object.entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import linkk.util.StringUtil;

public class GhostPlayer extends Player {
    public GhostPlayer(int x, int y, String name) {
        super(x, y, name);
    }

    public void draw(Graphics pen) {
        if (Player.main.getLevel() != this.getLevel())
            return;

        if (this.sprite != null) {
            Graphics2D g = (Graphics2D) pen;
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.25f));

            g.drawImage(sprite, x, y, null);
            g.setColor(Color.BLACK);
            pen.setFont(new Font("./gfx/Font/Peepo.ttf", 0, 24));

            int y = this.y;

            if (gravity == 1)
                y -= 24;

            else
                y += 24 + height;

            g.drawString(name, x + width / 2 - StringUtil.getWidth(g, name) / 2, y);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        }

        else if (this.color != null) {
            pen.setColor(this.color);
            pen.fillRect(x, y, width, height);
        }
    }

    public void update() {
        animation += animationSpeed;
        animation %= 2;
        controls();
    }

    public void controls() {
        if (direction == 1) {
            if (animation < 1)
                sprite = spriteSheet.getSprite(1);
            else
                sprite = spriteSheet.getSprite(4);
        }

        else if (direction == -1) {
            if (animation < 1)
                sprite = spriteSheet.getSprite(2);
            else
                sprite = spriteSheet.getSprite(5);
        }

        else {
            sprite = spriteSheet.getSprite(0);
        }
    }

    public void kill() {

    }

    public void keyPressed(KeyEvent ke) {

    }

    public void keyReleased(KeyEvent ke) {

    }
}
