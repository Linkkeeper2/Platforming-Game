package main.java.object;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GhostObject extends GameObject {
    public GhostObject(int x, int y, Color color) {
        super(x, y, 64, 64, color);
    }

    public GhostObject(int x, int y, String spritePath) {
        super(x, y, 64, 64, null);
        setSprite(spritePath);
    }

    public void draw(Graphics pen) {
        if (this.sprite != null) {
            Graphics2D g = (Graphics2D) pen;
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.25f));

            g.drawImage(sprite, x, y, null);
        }

        else if (this.color != null) {
            pen.setColor(this.color);
            pen.fillRect(x, y, width, height);
        }
    }
}
