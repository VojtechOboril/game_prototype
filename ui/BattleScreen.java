package ui;

import javax.swing.JButton;
import javax.swing.JPanel;

import game.GameValues;
import entities.Entity;
import entities.enemies.EnemyRat;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class BattleScreen extends JPanel {

    public BattleScreen(CardLayout cardLayout, JPanel cardPanel, GameValues gameValues) {

        // Generating Enemies
        ArrayList<Entity> enemies = new ArrayList<>();
        for(int i = 0; i < gameValues.currentFloor; i++) {
            enemies.add(new EnemyRat());
        }
        // Making them into JPanels
        ArrayList<JPanel> topPanels = new ArrayList<JPanel>();
        for(Entity enemy : enemies) {
            topPanels.add(enemy.getRepresentation());
        }

        // Making allies into JPanles
        ArrayList<JPanel> bottomPanels = new ArrayList<JPanel>();
        for(Entity ally : gameValues.heroes) {
            bottomPanels.add(ally.getRepresentation());
        }


        JPanel mainPanel = new JPanel(new GridLayout(2, 1));

        // Add top panels
        JPanel topPanel = new JPanel(new FlowLayout());
        for (JPanel panel : topPanels) {
            topPanel.add(panel);
        }
        mainPanel.add(topPanel);

        // Add bottom panels
        JPanel bottomPanel = new JPanel(new FlowLayout());
        for (JPanel panel : bottomPanels) {
            bottomPanel.add(panel);
        }
        mainPanel.add(bottomPanel);

        this.add(mainPanel);

        // Once the panel gets visible, start the battle
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                for(Entity a : gameValues.heroes) {
                    a.startProgressBarAnimation();
                }
                for(Entity a : enemies) {
                    a.startProgressBarAnimation();
                }
            }
        });

    }
    
}
