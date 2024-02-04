package game;
import entities.allies.AllySwordsman;

import java.util.ArrayList;

import entities.Entity;

public class GameValues {
    public int gold = 0;
    public int currentFloor = 1;
    public ArrayList<Entity> heroes = new ArrayList<Entity>();

    public GameValues() {
        heroes.add(new AllySwordsman());
    }
}
