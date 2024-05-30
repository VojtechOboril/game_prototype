package levels;

import entities.foes.FoeRat;

public class Level_1 extends LevelBase {
    
    public Level_1() {
        generateFoes();
        this.next_level = null;
        this.number = 1;
    }

    @Override
    protected void generateFoes() {
        for(int i = 0; i < 10; i++)
            this.foes.add(new FoeRat());
    }

    @Override
    public LevelBase getNextLevel() {
        return new Level_2();
    }
}
