package main.java.object.interact.behavior;

import main.java.object.entity.Player;

public class FlipBehavior implements InteractBehavior {
    public void start() {
        if (Player.main != null)
            Player.main.flipGravity();
    }

    public void run() {
    }

    public void end() {
    }
}
