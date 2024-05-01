package main.java.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;

import main.java.MyGame;
import main.java.screen.gui.Button;
import main.java.screen.gui.button.Editor;
import main.java.screen.gui.button.Start;

public class StartScreen extends Screen {
    public StartScreen() {
        objects.add(new Button(MyGame.SCREEN_WIDTH / 2 - 75, 25, 150, 50, Color.GRAY, "Start", new Start()));
        objects.add(new Button(MyGame.SCREEN_WIDTH / 2 - 75, 100, 150, 50, Color.GRAY, "Editor", new Editor()));
    }

    public void keyPressed(KeyEvent ke) {
        super.keyPressed(ke);

        switch (ke.getKeyCode()) {
            case 27: // ESCAPE
                System.exit(0);
                break;
        }
    }
}
