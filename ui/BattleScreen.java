package ui;

import javax.swing.JPanel;

import game.GameValues;
import entities.AttackQueue;
import entities.Entity;
import entities.foes.FoeRat;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class BattleScreen extends JPanel {

    public BattleScreen(CardLayout cardLayout, JPanel cardPanel) {

        // Generating Enemies
        for(int i = 0; i < GameValues.currentFloor; i++) {
            GameValues.addFoe(new FoeRat());
        }

        GameValues.setStarterHeroes();
        
        // Making them into JPanels
        ArrayList<JPanel> topPanels = new ArrayList<JPanel>();
        for(Entity enemy : GameValues.getFoes()) {
            topPanels.add(enemy.getRepresentation());
        }

        // Making allies into JPanles
        ArrayList<JPanel> bottomPanels = new ArrayList<JPanel>();
        for(Entity ally : GameValues.getHeroes()) {
            bottomPanels.add(ally.getRepresentation());
        }


        JPanel mainPanel = new JPanel(new GridLayout(3, 1));

        // Add top panels
        JPanel topPanel = new JPanel(new GridLayout(0, 5));
        for (JPanel panel : topPanels) {
            topPanel.add(panel);
        }
        mainPanel.add(topPanel);

        mainPanel.add(new JPanel());

        // Add bottom panels
        JPanel bottomPanel = new JPanel(new GridLayout(0, 5));
        for (JPanel panel : bottomPanels) {
            bottomPanel.add(panel);
        }
        mainPanel.add(bottomPanel);

        this.add(mainPanel);

        // Once the panel gets visible, start the battle
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                ArrayList<Entity> allEntities = new ArrayList<>(GameValues.getHeroes()) {{
                    addAll(GameValues.getFoes());
                }};
                AttackQueue aq = new AttackQueue(allEntities);
                aq.start();
            }
        });

    }
    
}
