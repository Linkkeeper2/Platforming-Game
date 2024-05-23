package main.java.screen;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import linkk.manager.SoundManager;
import main.java.MyGame;
import main.java.object.GameObject;
import main.java.object.block.Collidable;
import main.java.object.block.MovingPlatform;
import main.java.object.block.Platform;
import main.java.object.entity.Particle;
import main.java.object.entity.Player;
import main.java.screen.sub.SubScreen;

public abstract class Screen {
    protected static ArrayList<GameObject> objects;
    public static SubScreen subscreen;
    public static boolean debugMode;
    public static boolean[] globalControls = new boolean[2];
    protected static ArrayList<Thread> threads;

    public Screen() {
        objects = new ArrayList<>();

        if (threads == null)
            threads = new ArrayList<>();
        else {
            for (int i = 0; i < threads.size(); i++)
                threads.get(i).interrupt();

            threads.clear();
        }
        Collidable.collidables.clear();
        RoomScreen.resetPoints();
    }

    public static boolean subOn() {
        return subscreen != null;
    }

    public static void soundError() {
        MyGame.status.addMessage("Could not load sound.");
        SoundManager.setVolume(0);
        MyGame.status.addMessage("Game volume has been set to 0.");
    }

    public void draw(Graphics pen) {
        MyGame.status.draw(pen);

        for (int i = 0; i < objects.size(); i++) {
            GameObject obj = objects.get(i);

            if (obj != null && i < objects.size() && onScreen(obj))
                obj.draw(pen);
        }

        if (subscreen != null)
            subscreen.draw(pen);

        MyGame.fps.draw(pen);
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

        MyGame.fps.update();
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
        switch (ke.getKeyCode()) {
            case 37:
                globalControls[0] = false;
                break;

            case 39:
                globalControls[1] = false;
                break;
        }

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

    public static void addThread(Thread thread) {
        threads.add(thread);
        thread.start();
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

    public static boolean contains(GameObject obj) {
        return objects.contains(obj);
    }

    public static boolean containsThread(Thread obj) {
        return threads.contains(obj);
    }

    public static boolean[] getAdjacent(GameObject g) {
        boolean[] adjacent = new boolean[4];

        for (int i = 0; i < objects.size(); i++) {
            GameObject obj = objects.get(i);

            if (obj != null && obj instanceof Platform && !(obj instanceof MovingPlatform)) {
                if (obj.x == g.x - 64 && obj.y == g.y)
                    adjacent[0] = true;

                else if (obj.x == g.x + 64 && obj.y == g.y)
                    adjacent[2] = true;

                else if (obj.y == g.y - 64 && obj.x == g.x)
                    adjacent[1] = true;

                else if (obj.y == g.y + 64 && obj.x == g.x)
                    adjacent[3] = true;
            }
        }

        return adjacent;
    }

    public static void updateAdjacent() {
        for (int i = 0; i < objects.size(); i++) {
            GameObject obj = objects.get(i);

            if (obj != null && obj instanceof Collidable && !(obj instanceof MovingPlatform)) {
                Collidable c = (Collidable) obj;

                c.setEnabled();
            }
        }
    }

    public static boolean onScreen(GameObject obj) {
        return obj.x >= -obj.width && obj.y - obj.height <= MyGame.SCREEN_HEIGHT
                && obj.y >= -obj.height && obj.x - obj.width <= MyGame.SCREEN_WIDTH;
    }
}
