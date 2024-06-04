package main.java.object.entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.UnsupportedAudioFileException;

import linkk.manager.SoundManager;
import linkk.manager.SpriteSheetManager;
import linkk.manager.SpriteSheetManager.SpriteSheet;
import linkk.util.StringUtil;
import main.java.MyGame;
import main.java.screen.RoomScreen;
import main.java.screen.Screen;
import main.java.server.Account;

public class Player extends EntityBody {
    private boolean[] controls;
    public static Player main;
    private SpriteSheet sprites;
    protected String name;
    public static ArrayList<Player> players = new ArrayList<>();
    protected int currLevel;
    private double runCounter; // Animates the sprite for movement

    public Player(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        controls = new boolean[3];
        controls[0] = Screen.globalControls[0];
        controls[2] = Screen.globalControls[1];

        if (controls[0])
            direction = -1;

        if (controls[2])
            direction = 1;

        main = this;
        alive = true;
        sprites = SpriteSheetManager.getSheet("Player");
        sprite = sprites.getSprite(0);
        name = Account.name;
        xVel = 0;
        baseXVel = 10;
        new MovementThread(this).start();
    }

    public Player(int x, int y, String name) {
        super(x, y, 50, 50, new Color(0, 100, 255));
        controls = new boolean[3];
        alive = true;
        sprites = SpriteSheetManager.getSheet("Player");
        sprite = sprites.getSprite(0);
        this.name = name;
        players.add(this);
    }

    public void draw(Graphics pen) {
        super.draw(pen);
        pen.setColor(Color.BLACK);
        pen.setFont(new Font("./gfx/Font/Peepo.ttf", 0, 24));

        int y = this.y;

        if (gravity == 1)
            y -= 24;

        else
            y += 24 + height;

        pen.drawString(name, x + width / 2 - StringUtil.getWidth(pen, name) / 2, y);
    }

    public void update() {
        y += yVel * gravity;
        runCounter += 0.1;
        runCounter %= 2;

        super.update();
    }

    public void controls() {
        if (controls[0] || controls[2]) {
            if (xVel < baseXVel)
                xVel += 1;

            x += xVel * direction;

            hitbox.updateRect(x, y, width, height);

            if (direction == 1) {
                if (runCounter < 1)
                    sprite = sprites.getSprite(1);
                else
                    sprite = sprites.getSprite(4);
            }

            else if (direction == -1) {
                if (runCounter < 1)
                    sprite = sprites.getSprite(2);
                else
                    sprite = sprites.getSprite(5);
            }
        }

        else {
            Screen.globalControls[0] = controls[0];
            Screen.globalControls[1] = controls[2];
            xVel = 0;
        }

        if (controls[1] && !jumping && canJump) {
            jumping = true;
            canJump = false;
            new JumpThread(this).start();
        }
    }

    public void kill() {
        super.kill();
        RoomScreen.resetPlayer();
        try {
            SoundManager.playSound("./sfx/Player/Death.wav", 0);
        } catch (NullPointerException | UnsupportedAudioFileException | IOException e) {
        } catch (IllegalArgumentException | IllegalStateException e) {
            SoundManager.setVolume(0);
            MyGame.status.addMessage("Sounds failed to load.");
            MyGame.status.addMessage("Game volume has been set to 0.");
        }
    }

    public void keyPressed(KeyEvent ke) {
        super.keyPressed(ke);

        switch (ke.getKeyCode()) {
            case 37: // LEFT
                controls[0] = true;
                Screen.globalControls[0] = true;
                direction = -1;
                sprite = sprites.getSprite(2);
                break;

            case 38: // UP
                controls[1] = true;
                sprite = sprites.getSprite(3);
                break;

            case 39: // RIGHT
                controls[2] = true;
                Screen.globalControls[1] = true;
                direction = 1;
                sprite = sprites.getSprite(1);
                break;
        }
    }

    public void keyReleased(KeyEvent ke) {
        switch (ke.getKeyCode()) {
            case 37: // LEFT
                controls[0] = false;
                Screen.globalControls[0] = false;
                sprite = sprites.getSprite(0);
                break;

            case 38: // UP
                controls[1] = false;
                jumping = false;
                sprite = sprites.getSprite(0);
                break;

            case 39: // RIGHT
                controls[2] = false;
                Screen.globalControls[1] = true;
                sprite = sprites.getSprite(0);
                break;
        }
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return currLevel;
    }

    public void setLevel(int lvl) {
        currLevel = lvl;
    }

    public static void updatePlayers() {
        for (int i = 0; i < players.size(); i++)
            MyGame.database.updatePlayer(players.get(i));
    }

    public static void addPlayers() {
        for (int i = 0; i < players.size(); i++)
            Screen.add(players.get(i));
    }
}
