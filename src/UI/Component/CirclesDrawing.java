package UI.Component;

import Core.Model.Project;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CirclesDrawing extends JPanel {
    private String projectName;

    public CirclesDrawing(String projectName) {
        this.projectName = projectName;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCircle(g);
    }

    private void drawCircle(Graphics g) {
        int radius = 30;
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        g.setColor(Color.BLUE);
        g.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);

        g.setColor(Color.BLACK);
        g.drawString(projectName, centerX - radius, centerY + radius + 15);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 100); // Adjust as needed
    }
}

