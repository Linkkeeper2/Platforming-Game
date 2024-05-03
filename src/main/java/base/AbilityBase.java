package main.java.base;

import java.awt.event.KeyEvent;

import main.java.object.entity.EntityBody;

public abstract class AbilityBase implements Ability {
    protected EntityBody entity;
    protected int key;

    public AbilityBase(EntityBody entity, int key) {
        this.entity = entity;
        this.key = key;
    }

    public abstract void action();

    public abstract void run();

    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == key)
            run();
    }
}
