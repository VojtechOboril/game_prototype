package entities;

import javax.swing.*;

import game.GameValues;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AttackQueue {
    private List<Entity> entities;
    private Timer timer;

    public AttackQueue(List<Entity> entities) {
        this.entities = entities;
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
                    }
                }
            }
        });
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }
}
