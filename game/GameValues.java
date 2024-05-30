package game;
import entities.allies.AllySwordsman;

import java.util.ArrayList;
import java.util.Random;

import entities.Entity;

public class GameValues {
    public static int gold = 0;
    public static int currentFloor = 10;
    private static ArrayList<Entity> heroes = new ArrayList<Entity>();
    private static ArrayList<Entity> foes = new ArrayList<Entity>();

    private static ArrayList<Entity> aliveHeroes = new ArrayList<Entity>();
    private static ArrayList<Entity> aliveFoes = new ArrayList<Entity>();

    private static Random rand = new Random();

    public static void setStarterHeroes() {
        GameValues.heroes.clear();
        for(int i = 0; i < 10; i++) {
            GameValues.addHero(new AllySwordsman());
        }
    }

    public static void setHeroes(ArrayList<Entity> es) {
        heroes = es;
        aliveHeroes = es;
    }

    public static void setFoes(ArrayList<Entity> es) {
        foes = es;
        aliveFoes = es;
    }

    public static ArrayList<Entity> getHeroes() {
        return heroes;
    }

    public static ArrayList<Entity> getAliveHeroes() {
        return aliveHeroes;
    }

    public static ArrayList<Entity> getFoes() {
        return foes;
    }

    public static ArrayList<Entity> getAliveFoes() {
        return aliveFoes;
    }

    public static void addHero(Entity e) {
        heroes.add(e);
        aliveHeroes.add(e);
    }

    public static void addFoe(Entity e) {
        foes.add(e);
        aliveFoes.add(e);
    }

    public static void killHero(Entity e) {
        aliveHeroes.remove(e);
    }

    public static void killFoe(Entity e) {
        aliveFoes.remove(e);
    }

    public static ArrayList<Entity> getEntityEnemies(Entity e) {
        switch(e.getSide()) {
            case HEROES:
                return GameValues.foes;
            case ENEMIES:
                return GameValues.heroes;
        }
        return null;
    }

    public static ArrayList<Entity> getEntityAliveEnemies(Entity e) {
        switch(e.getSide()) {
            case HEROES:
                return GameValues.aliveFoes;
            case ENEMIES:
                return GameValues.aliveHeroes;
        }
        return null;
    }

    public static void killEntityEnemy(Entity e, Entity enemy) {
        switch(e.getSide()) {
            case HEROES:
                GameValues.killFoe(enemy);
            case ENEMIES:
                GameValues.killHero(enemy);
        }
    }

    public static Entity getRandomAliveEnemy(Entity e) {
        Entity chosenEnemy = GameValues.getEntityAliveEnemies(e).get(rand.nextInt(GameValues.getEntityAliveEnemies(e).size()));
        return chosenEnemy;
    }
}