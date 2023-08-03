package UI.Component;

import javax.swing.*;
import java.awt.*;

public class PanelSwitcher {
        private JPanel mainPanel;
        private CardLayout cardLayout;

        public PanelSwitcher() {
            mainPanel = new JPanel();
            cardLayout = new CardLayout();
            mainPanel.setLayout(cardLayout);
        }



        // Helper method to recursively enable components
        public void activateComponents(Component comp) {
            if (comp instanceof Container) {
                Component[] components = ((Container) comp).getComponents();
                for (Component c : components) {
                    c.setEnabled(true);
                    activateComponents(c);
                }
            }
        }

    }
