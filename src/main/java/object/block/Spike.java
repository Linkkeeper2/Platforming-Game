package main.java.object.block;

import java.awt.Rectangle;

import main.java.object.GameObject;
import main.java.object.entity.Player;

public class Spike extends Collidable {
    public Spike(int x, int y) {
        super(x, y, 64, 64, null);

        setSprite("./gfx/Objects/Spike.png");
    }

    public void collideAction(GameObject g) {
        if (getRect().intersects(g.getRect()))
            Player.main.kill();
    }

    public Rectangle getRect() {
        return new Rectangle(x + 16, y + 12, width - 32, height - 12);
    }
}
