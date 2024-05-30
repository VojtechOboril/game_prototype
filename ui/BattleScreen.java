package ui;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import game.GameValues;
import levels.Level_1;
import levels.LevelBase;
import entities.AttackQueue;
import entities.Entity;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class BattleScreen extends JPanel {
    ArrayList<JPanel> topPanels = new ArrayList<>();
    ArrayList<JPanel> bottomPanels = new ArrayList<>();
    JPanel mainPanel = new JPanel(new GridLayout(3, 1));
    LevelBase currentLevel;
    BattleScreen thisScreen = this;

    public BattleScreen(CardLayout cardLayout, JPanel cardPanel) {
        currentLevel = new Level_1();
        // Generating Entities
        GameValues.setFoes(currentLevel.getFoes());
        GameValues.setStarterHeroes();
        
        makeEntityPanels();
        makeMainPanel();
        startAttacking();
    }

    private void clearAllPanels() {
        mainPanel.removeAll();
        topPanels.clear();
        bottomPanels.clear();
    }

    private void makeEntityPanels() {
        for(Entity enemy : GameValues.getFoes()) {
            topPanels.add(enemy.getRepresentation());
        }
        for(Entity ally : GameValues.getHeroes()) {
            bottomPanels.add(ally.getRepresentation());
        }
    }

    private void makeMainPanel() {
        // Add top panels
        JPanel topPanel = new JPanel(new GridLayout(0, 5));
        for (JPanel panel : topPanels) {
            topPanel.add(panel);
        }
        mainPanel.add(topPanel);

        mainPanel.add(new JPanel()); // Middle empty panel

        // Add bottom panels
        JPanel bottomPanel = new JPanel(new GridLayout(0, 5));
        for (JPanel panel : bottomPanels) {
            bottomPanel.add(panel);
        }
        mainPanel.add(bottomPanel);
        
        this.add(mainPanel);
        this.revalidate();
        this.repaint();
    }
   
    public void nextLevel() {
        if(this.currentLevel.getNextLevel() == null) {
            System.out.println("no next level");
            return;
        }
        System.out.println("next level now!");
        this.currentLevel = this.currentLevel.getNextLevel();
        GameValues.setFoes(currentLevel.getFoes());
        GameValues.setStarterHeroes();
        clearAllPanels();
        makeEntityPanels();
        makeMainPanel();
        startAttacking();
        SwingUtilities.invokeLater(() -> {
            this.setVisible(false);  // Trigger the component event
            this.setVisible(true);   // Ensure componentShown is called
        });
    }

    private void startAttacking() {
        ComponentAdapter componentListener = new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                ArrayList<Entity> allEntities = new ArrayList<>(GameValues.getHeroes()) {{
                    addAll(GameValues.getFoes());
                }};
                AttackQueue aq = new AttackQueue(allEntities, thisScreen);
                aq.start();
                removeComponentListener(this);
            }
        };
        
        addComponentListener(componentListener);
    }
}
