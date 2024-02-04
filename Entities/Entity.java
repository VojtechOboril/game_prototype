package Entities;

public abstract class Entity {
    protected float fatigue = 1;
    protected float speed = 1;
    protected boolean alive = true;
    protected int attack;
    protected int hp;
    protected int armor;
    
    public abstract void useAbility(Entity target);
    
    public float getFatigue() {
        return this.fatigue;
    }
    
    public void increaseFatigue() {
        this.fatigue += 0.1;
    }
    
    public void reduceFatigue() {
        this.fatigue = (float)Math.min(this.fatigue - 0.1, 1.0);
    }
    
    public boolean isAlive() {
        return this.alive;
    }

    public float getSpeedWithFatigue() {
        return this.speed * this.fatigue;
    }

    public void getAttacked(int attack) {
        this.hp -= Math.max((int)(attack * attack / this.armor), 0);
    }
}