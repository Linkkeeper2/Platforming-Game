package main.java.screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import main.java.MyGame;
import main.java.object.GameObject;
import main.java.object.GhostObject;
import main.java.object.block.BlockType;
import main.java.object.block.Collidable;
import main.java.object.block.MovingPlatform;
import main.java.object.block.Platform;
import main.java.object.block.Spike;
import main.java.object.entity.Player;
import main.java.object.meta.EndTile;
import main.java.object.meta.StartTile;
import main.java.screen.gui.Button;
import main.java.screen.gui.Grid;
import main.java.screen.gui.Overlay;
import main.java.screen.gui.TextBox;
import main.java.screen.gui.button.BackToMenu;
import main.java.screen.gui.button.ButtonAction;
import main.java.screen.gui.button.LoadMap;
import main.java.screen.gui.button.ResetEditor;
import main.java.screen.gui.text.SaveThread;

public class EditorScreen extends Screen {
    private String object;
    private ArrayList<String> level;
    private int x, y;
    public static StartTile start;
    private EndTile end;
    private int rotation;
    private GhostObject selectedObject;
    private ArrayList<String> rotatables;

    public EditorScreen() {
        object = "Block";
        level = new ArrayList<>();
        Player.main = null;

        objects.add(new Grid(0, 0, 64, 64, MyGame.SCREEN_HEIGHT / 64, MyGame.SCREEN_WIDTH / 64));

        // GUI stuff
        Overlay o = new Overlay(0, MyGame.SCREEN_HEIGHT - 128, MyGame.SCREEN_WIDTH, 150, new Color(0, 0, 0, 128));
        objects.add(o);

        objects.add(new Button(10, MyGame.SCREEN_HEIGHT - 100, 150, 50, Color.GRAY, "Back to Menu", new BackToMenu()));

        objects.add(new Button(170, MyGame.SCREEN_HEIGHT - 100, 150, 50, Color.GRAY, "Save Level", new SaveMap()));

        objects.add(new Button(330, MyGame.SCREEN_HEIGHT - 100, 150, 50, Color.GRAY, "Reset Level", new ResetEditor()));

        objects.add(new Button(490, MyGame.SCREEN_HEIGHT - 100, 150, 50, Color.GRAY, "Test Level", new Test()));

        objects.add(new Button(650, MyGame.SCREEN_HEIGHT - 100, 150, 50, Color.GRAY, "Load Level", new LoadMap()));

        objects.add(new Button(810, MyGame.SCREEN_HEIGHT - 100, 150, 50, Color.GRAY, "Block", "./gfx/Editor/Block.png",
                new SwapObject("Block")));

        objects.add(new Button(970, MyGame.SCREEN_HEIGHT - 100, 150, 50, Color.GRAY, "Start", new SwapObject("Start")));
        objects.add(new Button(1130, MyGame.SCREEN_HEIGHT - 100, 150, 50, Color.GRAY, "End", "./gfx/Editor/Flag.png",
                new SwapObject("End")));

        objects.add(
                new Button(1290, MyGame.SCREEN_HEIGHT - 100, 150, 50, Color.GRAY, "Spike",
                        "./gfx/Editor/Death/Spike.png", new SwapObject("Spike")));

        objects.add(new Button(1450, MyGame.SCREEN_HEIGHT - 100, 150, 50, Color.GRAY, "Moving Block",
                new SwapObject("Moving Block")));

        selectedObject = new GhostObject(x, y, new Color(100, 100, 100, 64));

        rotatables = new ArrayList<>();

        rotatables.add("Spike");

        genBaseMap();
    }

    public void draw(Graphics pen) {
        super.draw(pen);

        if (selectedObject.y < MyGame.SCREEN_HEIGHT - 128 && !isObject(x - (x % 64), y - (y % 64)))
            selectedObject.draw(pen);
    }

    public void keyPressed(KeyEvent ke) {
        super.keyPressed(ke);

        switch (ke.getKeyCode()) {
            case 27: // ESCAPE
                MyGame.screen = new StartScreen();
                break;

            case 82: // R
                if (rotatables.contains(object)) {
                    rotation += 90;

                    if (rotation == 360)
                        rotation = 0;

                    setGhost();
                }
                break;
        }
    }

    public void mouseClicked(MouseEvent me) {
        super.mouseClicked(me);

        updateMouse(me);

        switch (me.getButton()) {
            case 1:
                placeObject(x, y);
                break;

            case 2:
                if (Player.main != null) {
                    Player.main.x = x;
                    Player.main.y = y;
                }
                break;

            case 3:
                removeObject(x, y);
                break;
        }
    }

    public void mouseMoved(MouseEvent me) {
        super.mouseMoved(me);

        updateMouse(me);
    }

    public void swap(String obj) {
        object = obj;
        rotation = 0;

        switch (object) {
            case "Start":
                selectedObject = new GhostObject(x, y, new Color(0, 255, 0, 64));
                break;

            case "End":
                selectedObject = new GhostObject(x, y, "./gfx/Objects/Flag.png");
                break;

            default:
                setGhost();
                break;
        }
    }

    public void genBaseMap() {
        int cols = MyGame.SCREEN_WIDTH / 64;
        int rows = MyGame.SCREEN_HEIGHT / 64;

        for (int r = 0; r < rows; r++) {
            String row = "";

            for (int c = 0; c < cols; c++) {
                row += "0";
            }

            level.add(row);
        }
    }

    private void setGhost() {
        if (object.equals("Moving Block")) {
            selectedObject = new GhostObject(x, y, new Color(0, 255, 255, 64));
            return;
        }

        String path = "./gfx/Objects/" + object;

        if (rotation != 0 && rotatables.contains(object))
            path += rotation + "";

        selectedObject = new GhostObject(x - (x % 64), y - (y % 64), path + ".png");
    }

    private void setTile(int row, int col, int type) {
        String tileRow = level.get(row);
        String newRow = "";

        for (int i = 0; i < tileRow.length(); i++) {
            if (i != col)
                newRow += tileRow.substring(i, i + 1);

            else
                newRow += type;
        }

        level.set(row, newRow);
        // System.out.println(map);
    }

    private void placeObject(int x, int y) {
        if (!(y < MyGame.SCREEN_HEIGHT - 128))
            return;

        boolean add = !isObject(x, y);

        if (add) {
            int row = ((y - (y % 64)) / 64);
            int col = ((x - (x % 64)) / 64);

            switch (object) {
                case "Block":
                    objects.add(new Platform(x - (x % 64), y - (y % 64), 64, 64, Color.GRAY, BlockType.SOLID));
                    setTile(row, col, 1);
                    break;

                case "Start":
                    if (!objects.contains(start)) {
                        objects.add(new StartTile(x - (x % 64), y - (y % 64)));
                        setTile(row, col, 2);
                        start = (StartTile) objects.getLast();
                    }
                    break;

                case "End":
                    if (!objects.contains(end)) {
                        objects.add(new EndTile(x - (x % 64), y - (y % 64)));
                        setTile(row, col, 3);
                        end = (EndTile) objects.getLast();
                    }
                    break;

                case "Spike":
                    objects.add(new Spike(x - (x % 64), y - (y % 64), rotation));
                    setTile(row, col, 4 + rotation / 90);
                    break;

                case "Moving Block":
                    objects.add(new MovingPlatform(x - (x % 64), y - (y % 64), 5, new Point(x - (x % 64), y - (y % 64)),
                            new Point(x - (x % 64) - 256, y - y % 64)));
                    setTile(row, col, 8);
                    break;
            }
        }
    }

    private void removeObject(int x, int y) {
        if (!(y < MyGame.SCREEN_HEIGHT - 128))
            return;

        for (int i = 0; i < objects.size(); i++) {
            GameObject obj = objects.get(i);

            if (obj != null && obj.originX == x - (x % 64) && obj.originY == y - (y % 64) && !(obj instanceof Grid)) {
                Screen.remove(obj);

                if (obj instanceof Platform)
                    Collidable.collidables.remove(obj);

                int row = ((y - (y % 64)) / 64);
                int col = ((x - (x % 64)) / 64);

                setTile(row, col, 0);

                if (obj instanceof StartTile)
                    start = null;

                if (obj instanceof EndTile)
                    end = null;
            }
        }
    }

    private boolean isObject(int x, int y) {
        if (!(y < MyGame.SCREEN_HEIGHT - 128))
            return false;

        for (int i = 0; i < objects.size(); i++) {
            GameObject obj = objects.get(i);

            if (obj != null && obj.originX == x - (x % 64) && obj.originY == y - (y % 64) && !(obj instanceof Grid)) {
                return true;
            }
        }

        return false;
    }

    private void updateMouse(MouseEvent me) {
        x = me.getX() - 8;
        y = me.getY() - 32;

        selectedObject.x = x - (x % 64);
        selectedObject.y = y - (y % 64);
    }

    public void setLevel(ArrayList<String> level) {
        this.level = level;
    }

    private class SaveMap implements ButtonAction {
        private TextBox box;

        public void action() {
            if (!Screen.contains(box))
                box = null;

            if (box == null) {
                box = new TextBox(8, MyGame.SCREEN_HEIGHT - 160, 250, "Level Name:");
                objects.add(box);
                new SaveThread(box, level).start();
            } else {
                Screen.remove(box);
                box = null;
            }
        }
    }

    private class SwapObject implements ButtonAction {
        private String object;

        public SwapObject(String obj) {
            this.object = obj;
        }

        public void action() {
            swap(this.object);
        }
    }

    private class Test implements ButtonAction {
        public void action() {
            int x = 0;
            int y = 0;

            if (start != null) {
                x = start.x;
                y = start.y;
            }

            if (Player.main == null)
                objects.add(new Player(x, y, 50, 50, new Color(0, 150, 255)));
            else
                remove(Player.main);
        }
    }
}
