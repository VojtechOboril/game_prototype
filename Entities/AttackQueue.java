package entities;

import javax.swing.*;

import game.GameValues;
import ui.BattleScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AttackQueue {
    private List<Entity> entities;
    private Timer timer;
    private BattleScreen bs;
    private Boolean fightEnded;

    public AttackQueue(List<Entity> entities, BattleScreen bs) {
        this.entities = entities;
        this.bs = bs;
        initializeTimer();
    }

    private void initializeTimer() {
        timer = new Timer(50, new ActionListener() { // Timer set to fire every 50ms
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Entity entity : entities) {
                    if(!entity.isAlive()) {
                        // entities.remove(entity);
                    }
                    else if(entity.getProgressBar() < 100) {
                        entity.incrementProgressBar();
                    }
                    else if (!GameValues.getEntityAliveEnemies(entity).isEmpty()) {
                        entity.resetProgressBar();
                        Entity chosenEnemy = GameValues.getRandomAliveEnemy(entity);
                        if (chosenEnemy != null) {
                            entity.useAbility(chosenEnemy);
                            if (!chosenEnemy.isAlive())
                                GameValues.killEntityEnemy(entity, chosenEnemy);
                        }
                    }
                    else {
                        ((Timer) e.getSource()).stop(); // Stop the timer if no entities are left
                        fightEnded = true;
                    }
                }
                if(fightEnded)
                    bs.nextLevel();
            }
        });
    }

    public void start() {
        this.fightEnded = false;
        timer.start();
    }

    public void stop() {
        timer.stop();
        bs.nextLevel();
    }
}
