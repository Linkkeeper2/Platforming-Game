package main.java.screen;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import main.java.object.GameObject;

public abstract class Screen {
    protected ArrayList<GameObject> objects;

    public Screen() {
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

            if (obj != null)
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
}
