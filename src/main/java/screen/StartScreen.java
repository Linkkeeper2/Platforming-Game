package main.java.screen;

import java.awt.Color;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

import linkk.manager.SoundManager;
import main.java.MyGame;
import main.java.object.entity.Player;
import main.java.screen.gui.Button;
import main.java.screen.gui.button.AccountMenu;
import main.java.screen.gui.button.Editor;
import main.java.screen.gui.button.Quit;
import main.java.screen.gui.button.Start;
import main.java.server.Account;

public class StartScreen extends Screen {
    public static boolean playMusic = true;

    public StartScreen() {
        String startContents;

        if (Account.level == 0)
            startContents = "Start";

        else
            startContents = "Con. from LVL " + (Account.level + 1);

        objects.add(new Button(MyGame.SCREEN_WIDTH / 2 - 75, 25, 150, 50, Color.GRAY, startContents, new Start()));
        objects.add(new Button(MyGame.SCREEN_WIDTH / 2 - 75, 100, 150, 50, Color.GRAY, "Editor", new Editor()));
        objects.add(new Button(MyGame.SCREEN_WIDTH / 2 - 75, 175, 150, 50, Color.GRAY, "Quit", new Quit()));
        objects.add(new Button(MyGame.SCREEN_WIDTH - 175, MyGame.SCREEN_HEIGHT - 100, 150, 50, Color.GRAY, "Account",
                new AccountMenu()));
        globalControls = new boolean[2];

        if (Player.main != null)
            MyGame.database.removePlayer(Account.name);

        GameScreen.clock = null;

        if (playMusic) {
            SoundManager.stopAllSounds();

            try {
                SoundManager.playSound("./sfx/Music/Welcome.wav", -1);
            } catch (NullPointerException | UnsupportedAudioFileException | IOException | IllegalArgumentException e) {
                Screen.soundError();
            }

            playMusic = false;
        }

        if (MyGame.frame != null)
            MyGame.frame.setBackground(new Color(170, 170, 170));
    }
}
