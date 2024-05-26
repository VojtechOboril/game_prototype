package entities.foes;
import entities.Entity;
import entities.Sides;;

public class FoeRat extends Entity {

    public FoeRat() {
        this.hp = 50;
        this.maxHp = hp;
        this.attack = 10;
        this.armor = 5;
        this.name = "Rat";
        this.side = Sides.ENEMIES;
    }
    
    @Override
    public void useAbility(Entity target) {
        target.getAttacked(this.attack);
    }
}
