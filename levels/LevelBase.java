package levels;

import java.util.ArrayList;

import entities.Entity;

public abstract class LevelBase {
    protected ArrayList<Entity> foes = new ArrayList<Entity>();
    protected int number;
    protected LevelBase next_level;

    protected abstract void generateFoes();

    public ArrayList<Entity> getFoes() {
        return this.foes;
    }

    public LevelBase getNextLevel() {
        return this.next_level;
    }
}
