package main.java.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import main.java.MyGame;
import main.java.object.GameObject;
import main.java.object.block.BlockType;
import main.java.object.block.Collidable;
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
import main.java.screen.gui.button.ResetEditor;
import main.java.screen.gui.text.SaveThread;

public class EditorScreen extends Screen {
    private String object;
    private ArrayList<String> level;
    private int x, y;
    public static StartTile start;
    private EndTile end;

    public EditorScreen() {
        object = "Block";
        level = new ArrayList<>();

        objects.add(new Grid(0, 0, 64, 64, MyGame.SCREEN_HEIGHT / 64, MyGame.SCREEN_WIDTH / 64));

        // GUI stuff
        Overlay o = new Overlay(0, MyGame.SCREEN_HEIGHT - 128, MyGame.SCREEN_WIDTH, 150, new Color(0, 0, 0, 128));
        objects.add(o);

        objects.add(new Button(10, MyGame.SCREEN_HEIGHT - 100, 150, 50, Color.GRAY, "Back to Menu", new BackToMenu()));

        objects.add(new Button(170, MyGame.SCREEN_HEIGHT - 100, 150, 50, Color.GRAY, "Save Level", new SaveMap()));

        objects.add(new Button(330, MyGame.SCREEN_HEIGHT - 100, 150, 50, Color.GRAY, "Reset Level", new ResetEditor()));

        objects.add(new Button(490, MyGame.SCREEN_HEIGHT - 100, 150, 50, Color.GRAY, "Test Level", new Test()));

        objects.add(new Button(650, MyGame.SCREEN_HEIGHT - 100, 150, 50, Color.GRAY, "Block", new SwapObject("Block")));

        objects.add(new Button(810, MyGame.SCREEN_HEIGHT - 100, 150, 50, Color.GRAY, "Start", new SwapObject("Start")));
        objects.add(new Button(970, MyGame.SCREEN_HEIGHT - 100, 150, 50, Color.GRAY, "End", new SwapObject("End")));

        objects.add(
                new Button(1130, MyGame.SCREEN_HEIGHT - 100, 150, 50, Color.GRAY, "Spike", new SwapObject("Spike")));

        genBaseMap();
    }

    public void keyPressed(KeyEvent ke) {
        super.keyPressed(ke);

        if (ke.getKeyCode() == 27)
            MyGame.screen = new StartScreen();
    }

    public void mouseClicked(MouseEvent me) {
        super.mouseClicked(me);

        updateMouse(me);

        if (me.getButton() == 1) {
            placeObject(x, y);
        }

        else if (me.getButton() == 3) {
            removeObject(x, y);
        }
    }

    public void swap(String obj) {
        object = obj;
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

        boolean add = true;

        for (int i = 0; i < objects.size(); i++) {
            GameObject obj = objects.get(i);

            if (obj != null && obj.x == x - (x % 64) && obj.y == y - (y % 64) && !(obj instanceof Grid)) {
                add = false;
            }
        }

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
                    objects.add(new Spike(x - (x % 64), y - (y % 64)));
                    setTile(row, col, 4);
                    break;
            }
        }
    }

    private void removeObject(int x, int y) {
        if (!(y < MyGame.SCREEN_HEIGHT - 128))
            return;

        for (int i = 0; i < objects.size(); i++) {
            GameObject obj = objects.get(i);

            if (obj.x == x - (x % 64) && obj.y == y - (y % 64) && !(obj instanceof Grid)) {
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

    private void updateMouse(MouseEvent me) {
        x = me.getX() - 8;
        y = me.getY() - 32;
    }

    private class SaveMap implements ButtonAction {
        public void action() {
            TextBox box = new TextBox(8, MyGame.SCREEN_HEIGHT - 160, 250, "Level Name:");
            objects.add(box);
            new SaveThread(box, level).start();
            // MyGame.status.addMessage("Map Saved!", 5000);
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
                objects.add(new Player(x, y, 50, 50, Color.CYAN));
            else
                remove(Player.main);
        }
    }
}