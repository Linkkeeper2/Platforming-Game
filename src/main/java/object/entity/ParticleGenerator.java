package main.java.object.entity;

import main.java.screen.Screen;

public class ParticleGenerator extends Thread {
    private int number;
    private EntityBody body;
    private int x;
    private int y;

    public ParticleGenerator(EntityBody body, int number) {
        this.number = number;
        this.body = body;
        this.x = body.x;
        this.y = body.y;
    }

    public void run() {
        for (int i = 0; i < number; i++) {
            if (!Screen.containsThread(this))
                break;

            Screen.add(new Particle(x + body.width / 2, y + body.height / 2, body.color, (byte) (i % 2)));
        }
    }
}
