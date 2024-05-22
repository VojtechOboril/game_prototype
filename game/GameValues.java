package game;
import entities.allies.AllySwordsman;

import java.util.ArrayList;

import entities.Entity;

public class GameValues {
    public int gold = 0;
    public int currentFloor = 10;
    public ArrayList<Entity> heroes = new ArrayList<Entity>();

    public GameValues() {
        for(int i = 0; i < 10; i++) {
            heroes.add(new AllySwordsman());
        }
    }
}
