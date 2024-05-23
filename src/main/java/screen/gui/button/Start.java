package main.java.screen.gui.button;

import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

import linkk.manager.SoundManager;
import main.java.MyGame;
import main.java.screen.RoomScreen;
import main.java.screen.Screen;
import main.java.server.Account;
import main.java.server.ServerUpdates;

public class Start implements ButtonAction {
    public void action() {
        MyGame.database.refreshLevels();
        MyGame.screen = new RoomScreen(0);
        MyGame.database.addPlayer(Account.name);

        try {
            SoundManager.playSound("./sfx/Music/Beginnings.wav", -1);
        } catch (NullPointerException | UnsupportedAudioFileException | IOException | IllegalArgumentException e) {
            Screen.soundError();
        }

        new ServerUpdates().start();
    }
}
