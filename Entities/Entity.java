package entities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public abstract class Entity {
    protected float fatigue = 10;
    protected float speed = 2;
    protected int attack;
    protected int hp;
    protected int maxHp;
    protected int armor;
    protected String name;
    protected JPanel panel;
    private JProgressBar progressBar;
    protected Sides side;
    private int progress = 0;

    public Entity() {
        this.maxHp = this.hp;
    }
    
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
        return this.hp > 0;
    }

    public float getSpeedWithFatigue() {
        return this.speed * this.fatigue;
    }

    public void getAttacked(int attack) {
        this.hp -= Math.max((int)(attack * attack / this.armor), 0);
        if (this.hp < 0) this.hp = 0;  // Ensure hp doesn't go below 0
        panel.repaint();
    }

    public void incrementProgressBar() {
        progress += 5000 / (getSpeedWithFatigue() * 100);
        progressBar.setValue(progress);
    }

    public int getProgressBar() {
        return progress;
    }

    public void resetProgressBar() {
        progress = 0;
        progressBar.setValue(progress);
    }

    public Sides getSide() {
        return side;
    }

    public JPanel getRepresentation() {
        this.panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Get the width and height of the panel
                int width = getWidth();
                int height = getHeight();

                // Paint the remaining HP portion
                g.setColor(Color.RED);
                g.fillRect(0, 0, width, height);

                // Paint the lost HP portion
                g.setColor(getBackground());
                g.fillRect(width, 0, -(int)(width * ((float)hp / maxHp)), height);
            }};
        this.panel.setLayout(new BorderLayout());
        this.panel.setPreferredSize(new Dimension(80, 40));
        this.panel.setBackground(Color.GREEN);  // Background color for HP bar

        // Create a JLabel with the letter and center it in the JPanel
        JLabel label = new JLabel(String.valueOf(name.charAt(0)), SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 12)); // Adjust the font size as needed

        // Set a border around the JLabel to represent the box
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        progressBar = new JProgressBar();
        progressBar.setPreferredSize(new Dimension(80, 10));
        //progressBar.setStringPainted(true);
        panel.add(progressBar, BorderLayout.SOUTH);

        this.panel.add(label, BorderLayout.CENTER);

        return panel;
    }
}
