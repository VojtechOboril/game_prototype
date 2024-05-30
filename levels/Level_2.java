package levels;

import entities.foes.FoeRat;

public class Level_2 extends LevelBase {
    
    public Level_2() {
        generateFoes();
        this.next_level = null;
        this.number = 1;
    }

    @Override
    protected void generateFoes() {
        for(int i = 0; i < 20; i++)
            this.foes.add(new FoeRat());
    }

}
