package main.java.object.interact.behavior;

import main.java.object.entity.JumpThread;
import main.java.object.entity.Player;

public class Boost implements InteractBehavior {
    public void start() {
        if (Player.main != null)
            new JumpThread(Player.main).start();
    }

    public void run() {
    }

    public void end() {
    }
}
