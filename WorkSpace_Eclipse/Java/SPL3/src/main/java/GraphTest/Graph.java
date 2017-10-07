package GraphTest;

import javax.swing.*;
import java.awt.*;

public class Graph {
    private JPanel contentPane;
    private InitialPanel initialPanel;
    private GraphPanel panel2;
    public void displayGraph(){
        JFrame frame = new JFrame("Card Layout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel contentPane = new JPanel();
        contentPane.setBorder(
                BorderFactory.createEmptyBorder(10, 15, 15, 15));
        contentPane.setLayout(new CardLayout());
        initialPanel = new InitialPanel(contentPane);
        panel2 = new GraphPanel();
        contentPane.add(initialPanel, "Panel 1");
        contentPane.add(new GraphPanel(), "Panel 2");
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setSize(500,500);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

    }

    public static void main(String... args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new Graph().displayGraph();
            }
        });
    }

}
