package main.java.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import org.bson.Document;

import main.java.MyGame;
import main.java.object.block.BlockType;
import main.java.object.block.Platform;
import main.java.object.block.Spike;
import main.java.object.entity.Player;
import main.java.object.meta.EndTile;
import main.java.object.meta.StartTile;

public class RoomScreen extends GameScreen {
    private String roomName;
    private static StartTile start;
    private EndTile end;
    private int level;

    public RoomScreen(int level) {
        this.level = level;
        this.roomName = "Test";

        if (!renderLevel(level)) {
            for (int i = 0; i < MyGame.SCREEN_WIDTH / 64; i++)
                objects.add(new Platform(i * 64, 512, 64, 64, Color.GRAY, BlockType.SOLID));
        }
    }

    public void update() {
        super.update();

        if (Player.main != null) {
            if (this.end != null) {
                if (Player.main.getRect().intersects(end.getRect()))
                    MyGame.screen = new RoomScreen(level + 1);
            }
        }
    }

    public void draw(Graphics pen) {
        super.draw(pen);

        pen.setFont(new Font("./gfx/Font/Peepo.ttf", 0, 20));
        pen.setColor(Color.BLACK);
        pen.drawString(roomName, 8, 32);
    }

    private boolean renderLevel(int level) {
        Document lvl = MyGame.database.getLevel(level);

        if (lvl == null)
            return false;

        roomName = lvl.getString("name");

        @SuppressWarnings("unchecked")
        ArrayList<String> data = (ArrayList<String>) lvl.get("data");

        for (int i = 0; i < data.size(); i++) {
            String row = data.get(i);

            for (int k = 0; k < row.length(); k++) {
                switch (row.substring(k, k + 1)) {
                    case "1":
                        objects.add(new Platform(k * 64, i * 64, 64, 64, Color.GRAY, BlockType.SOLID));
                        break;

                    case "2":
                        start = new StartTile(k * 64, i * 64);
                        Player.main.x = start.x;
                        Player.main.y = start.y;
                        break;

                    case "3":
                        end = new EndTile(k * 64, i * 64);
                        objects.add(end);
                        break;

                    case "4":
                        objects.add(new Spike(k * 64, i * 64));
                        break;
                }
            }
        }

        return true;
    }

    public static void resetPlayer() {
        if (start == null)
            return;

        Player.main.x = start.x;
        Player.main.y = start.y;
    }
}
