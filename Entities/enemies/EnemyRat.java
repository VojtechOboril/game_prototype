package entities.enemies;
import entities.Entity;;

public class EnemyRat extends Entity {

    public EnemyRat() {
        this.hp = 50;
        this.attack = 10;
        this.armor = 5;
        this.name = "Rat";
    }
    
    @Override
    public void useAbility(Entity target) {
        target.getAttacked(this.attack);
    }
}
