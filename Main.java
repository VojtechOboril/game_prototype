import javax.swing.*;
import java.awt.*;

import ui.MainScreen;

class Main {

    private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    
    public Main() {
        frame = new JFrame("Weird Merchant");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        cardPanel.add(new MainScreen(cardLayout, cardPanel), "MainScreen");

        cardLayout.show(cardPanel, "MainScreen");
        frame.add(cardPanel, BorderLayout.CENTER);

        frame.setSize(400, 600);
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}