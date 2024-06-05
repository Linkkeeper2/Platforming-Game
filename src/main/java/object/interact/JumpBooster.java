package main.java.object.interact;

import java.awt.Color;

import linkk.manager.SpriteSheetManager;
import linkk.manager.SpriteSheetManager.SpriteSheet;
import main.java.object.interact.behavior.Boost;

public class JumpBooster extends Interactable {
    private SpriteSheet spriteSheet;
    private double animation;

    public JumpBooster(int x, int y) {
        super(x, y, Color.GREEN);
        behavior = new Boost();
        spriteSheet = SpriteSheetManager.getSheet("JumpBooster");
        setSprite(spriteSheet.getSprite(0));
    }

    public void update() {
        super.update();

        animation += 0.3;
        animation %= 3;

        if (animation < 1)
            setSprite(spriteSheet.getSprite(0));

        else if (animation < 2)
            setSprite(spriteSheet.getSprite(1));

        else {
            setSprite(spriteSheet.getSprite(2));
        }
    }
}
