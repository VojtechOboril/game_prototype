package Entities.allies;
import Entities.Entity;

public class AllySwordsman extends Entity{

    public AllySwordsman() {
        this.hp = 60;
        this.attack = 15;
        this.armor = 8;
    }

    @Override
    public void useAbility(Entity target) {
        target.getAttacked(this.attack);
    }
    
}
