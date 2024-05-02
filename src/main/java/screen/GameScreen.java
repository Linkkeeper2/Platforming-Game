package main.java.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;

import main.java.object.entity.Player;
import main.java.screen.sub.PauseScreen;

public abstract class GameScreen extends Screen {
    public GameScreen() {
        objects.add(new Player(0, 0, 50, 50, new Color(0, 150, 255)));
    }

    public void keyPressed(KeyEvent ke) {
        super.keyPressed(ke);

        switch (ke.getKeyCode()) {
            case 27: // ESCAPE
                if (!Screen.subOn())
                    Screen.subscreen = new PauseScreen();
                else
                    Screen.subscreen = null;
                break;
        }
    }
}
