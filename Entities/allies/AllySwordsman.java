package entities.allies;
import entities.Entity;
import entities.Sides;

public class AllySwordsman extends Entity {

    public AllySwordsman() {
        this.hp = 60;
        this.maxHp = hp;
        this.attack = 15;
        this.armor = 8;
        this.name = "Swordsman";
        this.side = Sides.HEROES;
    }

    @Override
    public void useAbility(Entity target) {
        target.getAttacked(this.attack);
    }
    
}
