package Entities.enemies;
import Entities.Entity;;

public class EnemyRat extends Entity {

    public EnemyRat() {
        this.hp = 50;
        this.attack = 10;
        this.armor = 5;
    }
    
    @Override
    public void useAbility(Entity target) {
        target.getAttacked(this.attack);
    }
}
