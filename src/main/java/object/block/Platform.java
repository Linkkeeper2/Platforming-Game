package main.java.object.block;

import java.awt.Color;

import main.java.object.GameObject;

public class Platform extends Collidable {
    private BlockType type;

    public Platform(int x, int y, int width, int height, Color color, BlockType type) {
        super(x, y, width, height, color);

        this.type = type;
        setSprite("./gfx/Objects/Block.png");
    }

    public boolean[] getCollisions(GameObject g) {
        boolean[] collisions = new boolean[4];

        if (type == BlockType.SOLID) {
            if (enabled[0])
                collisions[0] = getLeft(4).intersects(g.getRight(g.width / 2));

            if (enabled[1])
                collisions[1] = getTop(4).intersects(g.getBottom(g.height / 2));

            if (enabled[2])
                collisions[2] = getRight(4).intersects(g.getLeft(g.width / 2));

            if (enabled[3])
                collisions[3] = getBottom(4).intersects(g.getTop(g.height / 2));
        }

        return collisions;
    }
}
