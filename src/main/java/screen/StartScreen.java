package main.java.screen;

import java.awt.Color;

import main.java.MyGame;
import main.java.screen.gui.Button;
import main.java.screen.gui.button.Editor;
import main.java.screen.gui.button.Quit;
import main.java.screen.gui.button.Start;

public class StartScreen extends Screen {
    public StartScreen() {
        objects.add(new Button(MyGame.SCREEN_WIDTH / 2 - 75, 25, 150, 50, Color.GRAY, "Start", new Start()));
        objects.add(new Button(MyGame.SCREEN_WIDTH / 2 - 75, 100, 150, 50, Color.GRAY, "Editor", new Editor()));
        objects.add(new Button(MyGame.SCREEN_WIDTH / 2 - 75, 175, 150, 50, Color.GRAY, "Quit", new Quit()));
        globalControls = new boolean[2];
    }
}
