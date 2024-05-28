package main.java.screen;

import java.awt.Color;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

import linkk.manager.SoundManager;
import main.java.MyGame;
import main.java.object.entity.Player;
import main.java.screen.gui.Button;
import main.java.screen.gui.button.Editor;
import main.java.screen.gui.button.Quit;
import main.java.screen.gui.button.Start;
import main.java.server.Account;

public class StartScreen extends Screen {
    public StartScreen() {
        objects.add(new Button(MyGame.SCREEN_WIDTH / 2 - 75, 25, 150, 50, Color.GRAY, "Start", new Start()));
        objects.add(new Button(MyGame.SCREEN_WIDTH / 2 - 75, 100, 150, 50, Color.GRAY, "Editor", new Editor()));
        objects.add(new Button(MyGame.SCREEN_WIDTH / 2 - 75, 175, 150, 50, Color.GRAY, "Quit", new Quit()));
        globalControls = new boolean[2];

        if (Player.main != null)
            MyGame.database.removePlayer(Account.name);

        GameScreen.clock = null;

        SoundManager.stopAllSounds();

        try {
            SoundManager.playSound("./sfx/Music/Welcome.wav", -1);
        } catch (NullPointerException | UnsupportedAudioFileException | IOException | IllegalArgumentException e) {
            Screen.soundError();
        }

        if (MyGame.frame != null)
            MyGame.frame.setBackground(new Color(170, 170, 170));
    }
}
