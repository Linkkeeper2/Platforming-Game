package main.java.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.java.object.meta.Hitbox;

public abstract class GameObject {
    public int x, y;
    public int originX, originY;
    public int width, height;
    public Color color;
    protected Color normalColor;
    public int direction;
    protected BufferedImage sprite;
    protected Hitbox hitbox;

    public GameObject(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.originX = x;
        this.originY = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.normalColor = color;

        if (!(this instanceof Hitbox))
            this.hitbox = new Hitbox(x, y, width, height, color);
    }

    public void draw(Graphics pen) {
        if (this.sprite != null)
            pen.drawImage(sprite, x, y, null);

        else if (this.color != null) {
            pen.setColor(this.color);
            pen.fillRect(x, y, width, height);

        }

        if (this.hitbox != null)
            hitbox.draw(pen);
    }

    public void update() {

    }

    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }

    public boolean isColliding(GameObject g) {
        return this.getRect().intersects(g.getRect());
    }

    public boolean isColliding(Rectangle r, Rectangle r2) {
        return r.intersects(r2);
    }

    public boolean isColliding(Rectangle other) {
        return other.intersects(getRect());
    }

    protected Color getDarkColor(int reduction) {
        int r = 0;
        int g = 0;
        int b = 0;

        if (this.normalColor.getRed() - reduction > 0) {
            r = this.normalColor.getRed() - reduction;
        }

        if (this.normalColor.getGreen() - reduction > 0) {
            g = this.normalColor.getGreen() - reduction;
        }

        if (this.normalColor.getBlue() - reduction > 0) {
            b = this.normalColor.getBlue() - reduction;
        }

        return new Color(r, g, b);
    }

    protected void moveTowards(GameObject other, int speed) {
        moveTowards(other.x, other.y, speed);
    }

    protected void moveTowards(int x, int y, int speed) {
        if (this.x < x) {
            this.x += speed;
            direction = 1;
        } else {
            this.x -= speed;
            direction = -1;
        }

        if (distance(x, true) <= speed)
            this.x = x;

        if (this.y < y)
            this.y += speed;
        else
            this.y -= speed;

        if (distance(y, false) <= speed)
            this.y = y;
    }

    protected int distance(GameObject other) {
        return distance(other.x, other.y);
    }

    protected int distance(int x, int y) {
        return Math.abs(this.x - x) + Math.abs(this.y - y);
    }

    protected int distance(int pos, boolean xAxis) {
        if (xAxis)
            return Math.abs(this.x - pos);

        return Math.abs(this.y - pos);
    }

    public Rectangle getLeft(int width) {
        return new Rectangle(x, y + 1, width, height - 1);
    }

    public Rectangle getRight(int width) {
        return new Rectangle(x + this.width - width, y + 1, width, height - 1);
    }

    public Rectangle getTop(int height) {
        return new Rectangle(x + 1, y, width - 1, height);
    }

    public Rectangle getBottom(int height) {
        return new Rectangle(x + 1, y + this.height - height, width - 1, height);
    }

    public void setSprite(String path) {
        try {
            if (!path.equals("")) {
                sprite = ImageIO.read(new File(path));
            } else {
                sprite = null;
            }
        } catch (IOException e) {
        }
    }

    public void keyTyped(KeyEvent ke) {
    }

    public void keyPressed(KeyEvent ke) {
    }

    public void keyReleased(KeyEvent ke) {
    }

    public void mouseClicked(MouseEvent me) {
    }

    public void mousePressed(MouseEvent me) {
    }

    public void mouseReleased(MouseEvent me) {
    }

    public void mouseEntered(MouseEvent me) {
    }

    public void mouseExited(MouseEvent me) {
    }

    public void mouseDragged(MouseEvent me) {
    }

    public void mouseMoved(MouseEvent me) {
    }
}
