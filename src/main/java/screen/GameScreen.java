package main.java.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;

import linkk.manager.SpriteSheetManager;
import main.java.MyGame;
import main.java.object.entity.Player;
import main.java.screen.gui.Clock;
import main.java.screen.sub.ChatScreen;
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
                if (!(Screen.subscreen instanceof ChatScreen)) {
                    Player.main.kill();
                    Screen.subscreen = null;
                }
                break;

            case 92: // \
                if (!Screen.subOn()) {
                    Screen.subscreen = new ChatScreen();
                    Player.main.controls[0] = false;
                    Player.main.controls[2] = false;
                    Player.main.setSprite(SpriteSheetManager.getSheet("Player").getSprite(0));
                } else
                    Screen.subscreen = null;
                break;
        }
    }
}
