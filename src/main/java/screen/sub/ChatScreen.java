package main.java.screen.sub;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import main.java.MyGame;
import main.java.screen.Screen;
import main.java.screen.gui.Overlay;
import main.java.screen.gui.text.ChatThread;
import main.java.screen.gui.text.TextBox;

public class ChatScreen extends SubScreen {
    private static boolean typing;
    private static TextBox box;
    private static Overlay overlay;

    public ChatScreen() {
        overlay = new Overlay(MyGame.chat.left, MyGame.chat.bottom - (MyGame.chat.getSize() + 1) * 28,
                MyGame.SCREEN_WIDTH - MyGame.chat.left, MyGame.SCREEN_HEIGHT, new Color(200, 200, 200, 64));
        objects.add(overlay);
    }

    public void update() {
        super.update();

        if (box != null) {
            if (box.isSubmitted()) {
                objects.remove(box);
                box = null;
            }
        }
    }

    public void draw(Graphics pen) {
        super.draw(pen);
        MyGame.chat.draw(pen);
    }

    public void keyPressed(KeyEvent ke) {
        super.keyPressed(ke);

        switch (ke.getKeyCode()) {
            case 47: // /
                if (!typing) {
                    box = new TextBox(MyGame.chat.left + 128, MyGame.chat.bottom - 20, 100, "");
                    objects.add(box);
                    new ChatThread(box).start();
                    typing = true;
                }
                break;
        }
    }

    public static void stopTyping() {
        typing = false;
        objects.remove(box);
    }

    public static void shiftOverlay() {
        if (overlay != null)
            overlay.y = MyGame.chat.bottom - (MyGame.chat.getSize() + 1) * 28;
    }
}
