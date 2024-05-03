package main.java.object.block;

import java.awt.Color;
import java.awt.Point;

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
}
