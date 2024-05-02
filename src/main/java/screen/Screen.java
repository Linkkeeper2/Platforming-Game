package main.java.screen;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import main.java.object.GameObject;
import main.java.object.block.Collidable;
import main.java.object.entity.Particle;
import main.java.object.entity.Player;
import main.java.screen.sub.SubScreen;

public abstract class Screen {
    protected static ArrayList<GameObject> objects;
    public static SubScreen subscreen;
    public static boolean debugMode;
    public static boolean[] globalControls = new boolean[2];

    public Screen() {
        objects = new ArrayList<>();
        Collidable.collidables.clear();
        RoomScreen.resetPoints();
    }

    public static boolean subOn() {
        return subscreen != null;
    }

    public void draw(Graphics pen) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject obj = objects.get(i);

            if (obj != null)
                obj.draw(pen);
        }

        if (subscreen != null)
            subscreen.draw(pen);
    }

    public void update() {
        if (subscreen != null)
            subscreen.update();

        else {
            for (int i = 0; i < objects.size(); i++) {
                GameObject obj = objects.get(i);

                if (obj != null && !(obj instanceof Particle))
                    obj.update();
            }
        }
    }

    public void keyTyped(KeyEvent ke) {
        if (subscreen != null)
            subscreen.keyTyped(ke);

        else {
            for (int i = 0; i < objects.size(); i++) {
                GameObject obj = objects.get(i);

                if (obj != null)
                    obj.keyTyped(ke);
            }
        }
    }

    public void keyPressed(KeyEvent ke) {
        if (subscreen != null)
            subscreen.keyPressed(ke);

        else {
            for (int i = 0; i < objects.size(); i++) {
                GameObject obj = objects.get(i);

                if (obj != null)
                    obj.keyPressed(ke);
            }

            if (ke.getKeyCode() == 192)
                debugMode = !debugMode;
        }
    }

    public void keyReleased(KeyEvent ke) {
        if (subscreen != null)
            subscreen.keyReleased(ke);

        else {
            for (int i = 0; i < objects.size(); i++) {
                GameObject obj = objects.get(i);

                if (obj != null)
                    obj.keyReleased(ke);
            }
        }
    }

    public void mouseClicked(MouseEvent me) {
        if (subscreen != null)
            subscreen.mouseClicked(me);

        else {
            for (int i = 0; i < objects.size(); i++) {
                GameObject obj = objects.get(i);

                if (obj != null)
                    obj.mouseClicked(me);
            }
        }
    }

    public void mousePressed(MouseEvent me) {
        if (subscreen != null)
            subscreen.mousePressed(me);

        else {
            for (int i = 0; i < objects.size(); i++) {
                GameObject obj = objects.get(i);

                if (obj != null)
                    obj.mousePressed(me);
            }
        }
    }

    public void mouseReleased(MouseEvent me) {
        if (subscreen != null)
            subscreen.mouseReleased(me);

        else {
            for (int i = 0; i < objects.size(); i++) {
                GameObject obj = objects.get(i);

                if (obj != null)
                    obj.mouseReleased(me);
            }
        }
    }

    public void mouseEntered(MouseEvent me) {
        if (subscreen != null)
            subscreen.mouseEntered(me);

        else {
            for (int i = 0; i < objects.size(); i++) {
                GameObject obj = objects.get(i);

                if (obj != null)
                    obj.mouseEntered(me);
            }
        }
    }

    public void mouseExited(MouseEvent me) {
        if (subscreen != null)
            subscreen.mouseExited(me);

        else {
            for (int i = 0; i < objects.size(); i++) {
                GameObject obj = objects.get(i);

                if (obj != null)
                    obj.mouseExited(me);
            }
        }
    }

    public void mouseDragged(MouseEvent me) {
        if (subscreen != null)
            subscreen.mouseDragged(me);

        else {
            for (int i = 0; i < objects.size(); i++) {
                GameObject obj = objects.get(i);

                if (obj != null)
                    obj.mouseDragged(me);
            }
        }
    }

    public void mouseMoved(MouseEvent me) {
        if (subscreen != null)
            subscreen.mouseMoved(me);

        else {
            for (int i = 0; i < objects.size(); i++) {
                GameObject obj = objects.get(i);

                if (obj != null)
                    obj.mouseMoved(me);
            }
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
