package main.java.screen.sub;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

import main.java.object.GameObject;
import main.java.object.entity.Particle;

public abstract class SubScreen {
    protected static ArrayList<GameObject> objects = new ArrayList<>();

    public SubScreen() {
        objects = new ArrayList<>();
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

    public void mouseWheelMoved(MouseWheelEvent me) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject obj = objects.get(i);

            if (obj != null)
                obj.mouseWheelMoved(me);
        }
    }

    public static void remove(GameObject obj) {
        objects.remove(obj);
    }

    public static void add(GameObject obj) {
        objects.add(obj);
    }
}
