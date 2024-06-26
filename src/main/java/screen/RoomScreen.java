package main.java.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.UnsupportedAudioFileException;

import org.bson.Document;

import linkk.manager.SoundManager;
import linkk.util.StringUtil;
import main.java.MyGame;
import main.java.object.block.BlockType;
import main.java.object.block.MovingPlatform;
import main.java.object.block.Platform;
import main.java.object.block.Spike;
import main.java.object.entity.Player;
import main.java.object.interact.GravityTile;
import main.java.object.interact.JumpBooster;
import main.java.object.meta.EndTile;
import main.java.object.meta.StartTile;
import main.java.server.Account;

public class RoomScreen extends GameScreen {
    private static String roomName;
    private static StartTile start;
    private static EndTile end;
    private int level;

    public RoomScreen(int level) {
        this.level = level;

        if (level % 25 == 0)
            initMusic();

        initPalette();
        Player.main.setLevel(level);
        Player.addPlayers();
        roomName = "Base Room";
        loadRoom(level);
    }

    public void initMusic() {
        if (level >= 0 && level < 25) {
            SoundManager.stopAllSounds();
            try {
                SoundManager.playSound("./sfx/Music/Beginnings.wav", -1);
            } catch (NullPointerException | UnsupportedAudioFileException | IOException
                    | IllegalArgumentException e) {
                Screen.soundError();
            }
        }

        else if (level >= 25 && level < 50) {
            SoundManager.stopAllSounds();
            try {
                SoundManager.playSound("./sfx/Music/Rookie.wav", -1);
            } catch (NullPointerException | UnsupportedAudioFileException | IOException
                    | IllegalArgumentException e) {
                Screen.soundError();
            }
        }

        else if (level >= 50 && level < 75) {
            SoundManager.stopAllSounds();
            try {
                SoundManager.playSound("./sfx/Music/Tense.wav", -1);
            } catch (NullPointerException | UnsupportedAudioFileException | IOException
                    | IllegalArgumentException e) {
                Screen.soundError();
            }
        }
    }

    private void initPalette() {
        if (level >= 0 && level < 25)
            MyGame.frame.setBackground(new Color(170, 170, 170));

        else if (level >= 25 && level < 50)
            MyGame.frame.setBackground(new Color(44, 48, 120));

        else if (level >= 50 && level < 75)
            MyGame.frame.setBackground(new Color(8, 11, 77));
    }

    @SuppressWarnings("static-access")
    public void update() {
        super.update();

        if (Player.main != null) {
            if (this.end != null) {
                if (Player.main.getRect().intersects(end.getRect())) {
                    MyGame.screen = new RoomScreen(level + 1);
                    Account.level = level + 1;
                    MyGame.database.updateUserLevel();
                }
            }
        }
    }

    public void draw(Graphics pen) {
        super.draw(pen);

        pen.setFont(new Font("./gfx/Font/Peepo.ttf", 0, 48));
        pen.setColor(Color.BLACK);
        pen.drawString(roomName, MyGame.SCREEN_WIDTH / 2 - StringUtil.getWidth(pen, roomName) / 2, 62);
        pen.setFont(new Font("./gfx/Font/Peepo.ttf", 0, 24));
        pen.drawString(level + 1 + "", 8, 32);
    }

    public void keyPressed(KeyEvent ke) {
        super.keyPressed(ke);

        if (ke.getKeyCode() == 61) // PLUS/EQUALS
            MyGame.screen = new RoomScreen(level + 1);

        else if (ke.getKeyCode() == 45) { // HYPHEN/UNDERSCORE
            if (level > 0)
                MyGame.screen = new RoomScreen(level - 1);

            else {
                MyGame.screen = new RoomScreen(MyGame.database.levels.size() - 1);
                level = MyGame.database.levels.size() - 1;
                initMusic();
            }
        }
    }

    public static void loadRoom(int level) {
        if (!renderLevel(level)) {
            start = new StartTile(0, 384);
            objects.add(start);
            objects.add(new GravityTile(256, 384));

            for (int i = 0; i < MyGame.SCREEN_WIDTH / 64; i++)
                objects.add(new Platform(i * 64, 512, 64, 64, Color.GRAY, BlockType.SOLID));

            for (int i = 0; i < MyGame.SCREEN_WIDTH / 64; i++)
                objects.add(new Platform(i * 64, 256, 64, 64, Color.GRAY, BlockType.SOLID));
        }
    }

    private static boolean renderLevel(int level) {
        Document lvl = MyGame.database.getLevel(level);

        if (lvl == null)
            return false;

        roomName = lvl.getString("name");

        @SuppressWarnings("unchecked")
        ArrayList<ArrayList<String>> data = (ArrayList<ArrayList<String>>) lvl.get("data");

        if (MyGame.screen instanceof EditorScreen) {
            EditorScreen e = (EditorScreen) MyGame.screen;
            e.setLevel(data);
        }

        for (int i = 0; i < data.size(); i++) {
            ArrayList<String> row = data.get(i);

            for (int k = 0; k < row.size(); k++) {
                String tile = row.get(k);

                switch (tile) {
                    case "1":
                        objects.add(new Platform(k * 64, i * 64, 64, 64, Color.GRAY, BlockType.SOLID));
                        break;

                    case "2":
                        start = new StartTile(k * 64, i * 64);

                        if (MyGame.screen instanceof EditorScreen) {
                            objects.add(start);
                            EditorScreen.start = start;
                        }

                        if (Player.main != null) {
                            Player.main.x = start.x;
                            Player.main.y = start.y;
                        }
                        break;

                    case "3":
                        end = new EndTile(k * 64, i * 64);
                        objects.add(end);

                        if (MyGame.screen instanceof EditorScreen) {
                            EditorScreen.end = end;
                        }
                        break;

                    case "8":
                        objects.add(new MovingPlatform(k * 64, i * 64, 5));
                        break;

                    case "9":
                        objects.add(new JumpBooster(k * 64, i * 64));
                        break;

                    case "10":
                        objects.add(new GravityTile(k * 64, i * 64));
                        break;
                }

                if (Integer.parseInt(tile) >= 4 && Integer.parseInt(tile) <= 7)
                    objects.add(new Spike(k * 64, i * 64, (Integer.parseInt(tile) - 4) * 90));
            }
        }

        Screen.updateAdjacent();

        return true;
    }

    public static void resetPlayer() {
        if (MyGame.screen instanceof EditorScreen) {
            Player.main.x = 0;
            Player.main.y = 0;

            if (EditorScreen.start != null) {
                Player.main.x = EditorScreen.start.x;
                Player.main.y = EditorScreen.start.y;
            }
        }

        if (start == null)
            return;

        Player.main.x = start.x;
        Player.main.y = start.y;
    }

    public static void resetPoints() {
        start = null;
        end = null;
    }

    public static String getRoomName() {
        return roomName;
    }
}
