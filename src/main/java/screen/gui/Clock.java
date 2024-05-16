package main.java.screen.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

import main.java.MyGame;
import main.java.object.GameObject;
import main.java.screen.Screen;

public class Clock extends GameObject {
    private int seconds;
    private int minutes;
    private int hours;
    private int milliseconds;
    private boolean running;
    private ClockThread clockThread;

    public Clock(int x, int y, Color color) {
        super(x, y, 0, 0, color);
    }

    public void start() {
        if (clockThread != null) {
            clockThread.interrupt();
            clockThread = null;
        }

        clockThread = new ClockThread();
        clockThread.start();
        running = true;
        Screen.add(this);
    }

    public void end() {
        running = false;
        clockThread.interrupt();
        clockThread = null;
    }

    public void draw(Graphics pen) {
        pen.setColor(color);
        pen.setFont(new Font("./gfx/Font/Peepo.ttf", 0, 24));
        String mil = milliseconds + "";
        mil = mil.substring(0, mil.length() - 1);

        String sec = "0" + seconds;

        if (sec.length() > 2)
            sec = sec.substring(1, sec.length());

        String min = "0" + minutes;

        if (min.length() > 2)
            min = min.substring(1, min.length());

        pen.drawString(hours + ":" + min + ":" + sec + "." + mil, x, y);
    }

    public boolean isRunning() {
        return running;
    }

    private void adjustClock() {
        if (milliseconds >= 1000) {
            milliseconds = 0;
            seconds++;
        }

        if (seconds >= 60) {
            seconds = 0;
            minutes++;
        }

        if (minutes >= 60) {
            minutes = 0;
            hours++;
        }
    }

    private class ClockThread extends Thread {
        public void run() {
            while (running) {
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (!Screen.subOn()) {
                    double add = 1000 / (double) MyGame.fps.getFPS();
                    milliseconds += add;

                    adjustClock();
                }
            }
        }
    }
}
