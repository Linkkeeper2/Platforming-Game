package main.java.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;

import main.java.MyGame;
import main.java.object.entity.Player;
import main.java.screen.gui.Clock;
import main.java.screen.sub.PauseScreen;

public abstract class GameScreen extends Screen {
    public static Clock clock;

    public GameScreen() {
        objects.add(new Player(0, 0, 50, 50, new Color(0, 150, 255)));

        if (clock == null) {
            clock = new Clock(8, MyGame.SCREEN_HEIGHT - 64, Color.BLACK);
            clock.start();
        } else {
            Screen.add(clock);
        }

        StartScreen.playMusic = true;
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

            case 82: // R
                Player.main.kill();
                Screen.subscreen = null;
                break;
        }
    }
}
