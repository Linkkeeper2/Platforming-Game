package main.java;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class Game implements KeyListener, MouseListener, MouseMotionListener {
    private JFrame frame;
    private GamePanel gamePanel;
    boolean running;
    private MyGame game;

    protected void start(String title, int width, int height) {
        this.game = (MyGame) this;
        running = true;
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamePanel = new GamePanel();
        frame.getContentPane().add(BorderLayout.CENTER, gamePanel);
        frame.setResizable(false);
        frame.setSize(width, height);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setBackground(new Color(200, 200, 200));
        frame.addKeyListener(this);
        frame.addMouseListener(this);
        frame.addMouseMotionListener(this);
        run();
    }

    class GamePanel extends JPanel {
        private static final long serialVersionUID = 1L;

        @Override
        public void paintComponent(Graphics g) {
            game.draw(g);
        }
    }

    private void run() {
        while (running) {
            game.update();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
            frame.repaint();
        }
    }
}
