package ui;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends JPanel{
    public MainScreen(CardLayout cardLayout, JPanel cardPanel) {
        JButton newGameButton = new JButton("New Game");

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "CraftersScreen");
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(newGameButton);

        this.add(buttonPanel, BorderLayout.SOUTH);
    }
}
