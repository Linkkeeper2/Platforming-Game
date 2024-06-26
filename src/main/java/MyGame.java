package main.java;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.IOException;
import java.util.Timer;

import linkk.collection.DuplicateKeyException;
import linkk.manager.SpriteSheetManager;
import main.java.screen.Screen;
import main.java.screen.StartScreen;
import main.java.screen.gui.FPSCounter;
import main.java.server.Database;
import main.java.server.ServerChat;
import main.java.server.ServerStatus;

public class MyGame extends Game {
    public static final String TITLE = "Platforming Game";
    public static final int SCREEN_WIDTH = 1728;
    public static final int SCREEN_HEIGHT = 896;

    public static Timer timer = new Timer();
    public static Screen screen;
    public static Database database;
    public static ServerStatus status = new ServerStatus();
    public static FPSCounter fps = new FPSCounter(SCREEN_WIDTH - 100, 24, Color.BLACK);
    public static ServerChat chat = new ServerChat();

    public MyGame() {
        screen = new StartScreen();
        database = new Database();
        initSprites();
        fps.start();
        chat.left = MyGame.SCREEN_WIDTH - 512;
        chat.bottom = MyGame.SCREEN_HEIGHT - 64;
    }

    public void initSprites() {
        try {
            SpriteSheetManager.addSheet("Player", 50, 50, "./gfx/Player/Player.png");
            SpriteSheetManager.addSheet("JumpBooster", 64, 64, "./gfx/Objects/JumpBooster.png");
            SpriteSheetManager.addSheet("Flag", 64, 64, "./gfx/Objects/Flag.png");
            SpriteSheetManager.addSheet("Block", 64, 64, "./gfx/Objects/Block.png");
            SpriteSheetManager.addSheet("MovingBlock", 64, 64, "./gfx/Objects/MovingBlock.png");
            SpriteSheetManager.addSheet("GravityTile", 64, 64, "./gfx/Objects/GravityTile.png");
        } catch (IOException | DuplicateKeyException e) {
            status.addMessage("Failed to load sprites.", 10000);
        }
    }

    public void update() {
        screen.update();
    }

    public void draw(Graphics pen) {
        screen.draw(pen);
    }

    public void keyTyped(KeyEvent ke) {
        screen.keyTyped(ke);
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
        screen.mouseEntered(me);
    }

    public void mouseExited(MouseEvent me) {
        screen.mouseExited(me);
    }

    public void mouseDragged(MouseEvent me) {
        screen.mouseDragged(me);
    }

    public void mouseMoved(MouseEvent me) {
        screen.mouseMoved(me);
    }

    public void mouseWheelMoved(MouseWheelEvent me) {
        screen.mouseWheelMoved(me);
    }

    // Launches the Game
    public static void main(String[] args) {
        new MyGame().start(TITLE, SCREEN_WIDTH, SCREEN_HEIGHT);
    }
}
