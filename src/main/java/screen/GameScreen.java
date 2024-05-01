package main.java.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;

import main.java.MyGame;
import main.java.object.entity.Player;

public abstract class GameScreen extends Screen {
    public GameScreen() {
        objects.add(new Player(0, 0, 50, 50, Color.CYAN));
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
