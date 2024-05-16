package main.java.object.interact.behavior;

import main.java.object.entity.ExternalJumpThread;
import main.java.object.entity.Player;

public class Boost implements InteractBehavior {
    public void start() {
        if (Player.main != null)
            new ExternalJumpThread(Player.main).start();
    }

    public void run() {
    }

    public void end() {
    }
}
