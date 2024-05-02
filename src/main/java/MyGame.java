package main.java;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Timer;

import main.java.screen.Screen;
import main.java.screen.StartScreen;
import main.java.server.Database;

// NOTE TO SELF: FIX LOADING LEVELS IN EDITOR NOT UPDATING DATA ARRAY

public class MyGame extends Game {
    public static final String TITLE = "Platforming Game";
    public static final int SCREEN_WIDTH = 1728;
    public static final int SCREEN_HEIGHT = 896;

    public static Timer timer = new Timer();
    public static Screen screen;
    public static Database database;

    public MyGame() {
        screen = new StartScreen();
        database = new Database();
    }

    public void update() {
        screen.update();
    }

    public void draw(Graphics pen) {
        screen.draw(pen);
    }

    public void keyTyped(KeyEvent ke) {
    }

    public void keyPressed(KeyEvent ke) {
        screen.keyPressed(ke);
    }

    public void keyReleased(KeyEvent ke) {
        screen.keyReleased(ke);
    }

    public void mouseClicked(MouseEvent me) {
        screen.mouseClicked(me);
    }

    public void mousePressed(MouseEvent me) {
        screen.mousePressed(me);
    }

    public void mouseReleased(MouseEvent me) {
        screen.mouseReleased(me);
    }

    public void mouseEntered(MouseEvent me) {
    }

    public void mouseExited(MouseEvent me) {
    }

    public void mouseDragged(MouseEvent me) {
        screen.mouseDragged(me);
    }

    public void mouseMoved(MouseEvent me) {
        screen.mouseMoved(me);
    }

    // Launches the Game
    public static void main(String[] args) {
        new MyGame().start(TITLE, SCREEN_WIDTH, SCREEN_HEIGHT);
    }
}
