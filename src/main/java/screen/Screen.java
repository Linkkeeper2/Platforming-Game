package main.java.screen;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import main.java.object.GameObject;
import main.java.object.block.Collidable;
import main.java.object.entity.Particle;
import main.java.object.entity.Player;

public abstract class Screen {
    protected static ArrayList<GameObject> objects;
    public static boolean debugMode;

    public Screen() {
        objects = new ArrayList<>();
        Collidable.collidables.clear();
        RoomScreen.resetPoints();
    }

    public void draw(Graphics pen) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject obj = objects.get(i);

            if (obj != null)
                obj.draw(pen);
        }
    }

    public void update() {
        for (int i = 0; i < objects.size(); i++) {
            GameObject obj = objects.get(i);

            if (obj != null && !(obj instanceof Particle))
                obj.update();
        }
    }

    public void keyTyped(KeyEvent ke) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject obj = objects.get(i);

            if (obj != null)
                obj.keyTyped(ke);
        }
    }

    public void keyPressed(KeyEvent ke) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject obj = objects.get(i);

            if (obj != null)
                obj.keyPressed(ke);
        }

        if (ke.getKeyCode() == 192)
            debugMode = !debugMode;
    }

    public void keyReleased(KeyEvent ke) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject obj = objects.get(i);

            if (obj != null)
                obj.keyReleased(ke);
        }
    }

    public void mouseClicked(MouseEvent me) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject obj = objects.get(i);

            if (obj != null)
                obj.mouseClicked(me);
        }
    }

    public void mousePressed(MouseEvent me) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject obj = objects.get(i);

            if (obj != null)
                obj.mousePressed(me);
        }
    }

    public void mouseReleased(MouseEvent me) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject obj = objects.get(i);

            if (obj != null)
                obj.mouseReleased(me);
        }
    }

    public void mouseEntered(MouseEvent me) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject obj = objects.get(i);

            if (obj != null)
                obj.mouseEntered(me);
        }
    }

    public void mouseExited(MouseEvent me) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject obj = objects.get(i);

            if (obj != null)
                obj.mouseExited(me);
        }
    }

    public void mouseDragged(MouseEvent me) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject obj = objects.get(i);

            if (obj != null)
                obj.mouseDragged(me);
        }
    }

    public void mouseMoved(MouseEvent me) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject obj = objects.get(i);

            if (obj != null)
                obj.mouseMoved(me);
        }
    }

    public static void remove(GameObject obj) {
        objects.remove(obj);

        if (obj instanceof Collidable)
            Collidable.collidables.remove(obj);

        else if (obj instanceof Player)
            if (obj.equals(Player.main))
                Player.main = null;
    }

    public static void add(GameObject obj) {
        objects.add(obj);
    }
}
