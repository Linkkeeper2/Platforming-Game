package main.java.object.meta;

import java.awt.Color;

import main.java.object.GameObject;

public class EndTile extends GameObject {
    public EndTile(int x, int y) {
        super(x, y, 64, 64, Color.YELLOW);

        setSprite("./gfx/Objects/Flag.png");
        setSheet("Flag");
        initAnimation(true, 0.15);
    }
}
