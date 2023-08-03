package UI.Component;

import javax.swing.*;
import java.awt.*;

public class Circle extends JPanel {
    private Color circleColor;
    private int circleX, circleY, circleWidth, circleHeight;

    public Circle(Color color, int x, int y, int width, int height) {
        this.circleColor = color;
        this.circleX = x;
        this.circleY = y;
        this.circleWidth = width;
        this.circleHeight = height;
        setVisible(true);
        setBounds(x,y,width,height);
        setBackground(new Color(0,0,0,0));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(circleColor);
        g.drawOval(40, 105, 55, 55);
    }

//    @Override
//    public Dimension getPreferredSize() {
//        return new Dimension(400, 400);
//    }

}
