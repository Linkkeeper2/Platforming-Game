package main.java.object.block;

import java.awt.Color;

import main.java.object.GameObject;
import main.java.object.entity.Player;
import main.java.object.meta.Hitbox;

public class Spike extends Collidable {
    public Spike(int x, int y) {
        super(x, y, 64, 64, null);

        setSprite("./gfx/Objects/Spike.png");
        this.hitbox = new Hitbox(x + 16, y + 12, width - 32, height - 12, Color.RED);
    }

    public void collideAction(GameObject g) {
        if (hitbox.getRect().intersects(g.getRect()))
            Player.main.kill();
    }
}
