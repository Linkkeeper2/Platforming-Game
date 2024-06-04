package main.java.object.block;

import java.awt.Color;
import java.awt.Point;

import main.java.object.GameObject;

public class MovingPlatform extends Platform {
    private int speed;
    private Point p1;
    private Point p2;

    public MovingPlatform(int x, int y, int speed, Point p1, Point p2) {
        super(x, y, 64, 64, Color.CYAN, BlockType.SOLID);
        direction = 1;
        this.speed = speed;
        this.p1 = p1;
        this.p2 = p2;
        setSprite("./gfx/Objects/MovingBlock.png");
    }

    public MovingPlatform(int x, int y, int speed) {
        super(x, y, 64, 64, Color.CYAN, BlockType.SOLID);
        direction = 1;
        this.speed = speed;
        this.p1 = new Point(x, y);
        this.p2 = new Point(x - 256, y);
        setSprite("./gfx/Objects/MovingBlock.png");
    }

    public void update() {
        if (direction == 1) {
            moveTowards(p1.x, p1.y, speed);

            if (distance(p1.x, p1.y) < speed)
                direction = -1;
        } else {
            moveTowards(p2.x, p2.y, speed);

            if (distance(p2.x, p2.y) < speed)
                direction = 1;
        }

        hitbox.updateRect(x, y, width, height);
    }

    public boolean[] getCollisions(GameObject g) {
        boolean[] collisions = super.getCollisions(g);

        if (collisions[1]) {
            g.x += speed * direction;
            g.y = y - g.height;
        }

        return collisions;
    }
}
