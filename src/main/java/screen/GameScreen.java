package main.java.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;

import main.java.MyGame;
import main.java.object.block.BlockType;
import main.java.object.block.Platform;
import main.java.object.entity.Player;

public class GameScreen extends Screen {
    public GameScreen() {
        objects.add(new Player(0, 0, 50, 50, Color.RED));

        for (int i = 0; i < MyGame.SCREEN_WIDTH / 64; i++)
            objects.add(new Platform(i * 64, 256, 64, 64, Color.GRAY, BlockType.SOLID));

        objects.add(new Platform(256, 128, 64, 64, Color.GRAY, BlockType.SOLID));
    }

    public void keyPressed(KeyEvent ke) {
        super.keyPressed(ke);

        switch (ke.getKeyCode()) {
            case 27: // ESCAPE
                MyGame.screen = new StartScreen();
                break;
        }
    }
}
