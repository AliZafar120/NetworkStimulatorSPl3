package GraphTest;

import javax.swing.*;
import java.awt.*;

public class GraphPanel extends JPanel{
/*
    public void paint(Graphics g) {
        super.paintComponent(g);
        g.drawRect(230,80,10,10);
        g.setColor(Color.RED);
        g.fillRect(230,80,10,10);

    }*/

    private String sShape = "Line";
    public void setShape(String shape)
    {
        sShape = shape;
        repaint();
    }
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if ("Line".equalsIgnoreCase(sShape))
        {
            g.drawLine(10, 20, 500, 500);
        }
        else if ("Circle".equalsIgnoreCase(sShape))
        {
            g.drawOval(50, 100 , 200, 200);
        }
        else if ("Rectangle".equalsIgnoreCase(sShape))
        {
            g.drawRect(10, 20, 150, 200);
        }
    }
}
