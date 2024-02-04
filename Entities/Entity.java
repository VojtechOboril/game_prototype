package entities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public abstract class Entity {
    protected float fatigue = 1;
    protected float speed = 1;
    protected boolean alive = true;
    protected int attack;
    protected int hp;
    protected int armor;
    protected String name;
    protected JPanel panel;
    private JProgressBar progressBar;
    
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

    public JPanel getRepresentation() {
        this.panel = new JPanel();
        this.panel.setLayout(new BorderLayout());

        // Create a JLabel with the letter and center it in the JPanel
        JLabel label = new JLabel(String.valueOf(name.charAt(0)), SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 24)); // Adjust the font size as needed

        // Set a border around the JLabel to represent the box
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        panel.add(progressBar, BorderLayout.SOUTH);

        this.panel.add(label, BorderLayout.CENTER);

        return panel;
    }

    public void startProgressBarAnimation() {
        Timer timer = new Timer((int)(getSpeedWithFatigue() * 10), new ActionListener() {
            int progress = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (progress <= 100) {
                    progressBar.setValue(progress);
                    progress++;
                } else {
                    progress = 0;
                }
                // Once it dies, it stops doing stuff
                if(!isAlive()) {
                    ((Timer) e.getSource()).stop();
                }
            }
        });

        timer.start();
    }


}