package main.java.object.entity;

import main.java.MyGame;
import main.java.screen.Screen;

public class ParticleThread extends Thread {
    private Particle particle;

    public ParticleThread(Particle p) {
        this.particle = p;
    }

    public void run() {
        while (particle.y < MyGame.SCREEN_HEIGHT) {
            if (!Screen.subOn())
                particle.update();
            try {
                sleep(10);
            } catch (InterruptedException e) {
            }
        }

        Screen.remove(particle);
    }
}
