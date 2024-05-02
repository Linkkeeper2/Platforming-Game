package main.java.object.block;

import java.awt.Color;

import main.java.object.GameObject;
import main.java.object.entity.Player;
import main.java.object.meta.Hitbox;

public class Spike extends Collidable {
    public Spike(int x, int y, int rotation) {
        super(x, y, 64, 64, null);

        if (rotation != 0) {
            setSprite("./gfx/Objects/Spike" + rotation + ".png");

            switch (rotation) {
                case 180:
                    this.hitbox = new Hitbox(x + 16, y, width - 32, height - 12, Color.RED);
                    break;

                case 90:
                    this.hitbox = new Hitbox(x, y + 16, width - 12, height - 32, Color.RED);
                    break;

                case 270:
                    this.hitbox = new Hitbox(x + 12, y + 16, width - 12, height - 32, Color.RED);
                    break;
            }
        }

        else {
            setSprite("./gfx/Objects/Spike.png");
            this.hitbox = new Hitbox(x + 16, y + 12, width - 32, height - 12, Color.RED);
        }
    }

    public void collideAction(GameObject g) {
        if (hitbox.getRect().intersects(g.getRect()))
            Player.main.kill();
    }
}
