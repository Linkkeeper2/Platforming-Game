package main.java.object.interact.behavior;

import java.util.TimerTask;

import main.java.MyGame;
import main.java.object.entity.Player;

public class FlipBehavior implements InteractBehavior {
    private boolean canFlip;

    public FlipBehavior() {
        canFlip = true;
    }

    public void start() {
        if (Player.main != null && canFlip) {
            Player.main.flipGravity();
            Player.main.stopJumping();
            canFlip = false;

            MyGame.timer.schedule(new TimerTask() {
                public void run() {
                    canFlip = true;
                }
            }, 50);
        }
    }

    public void run() {
    }

    public void end() {
    }
}
