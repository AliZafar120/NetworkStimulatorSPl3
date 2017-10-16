package GraphTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Graph {
    private JPanel contentPane;
    private InitialPanel initialPanel;
    private GraphPanel graphpanel;
    private LogPanel logpanel;
    private NodeLogPanel nodelogpanel;
    private SearchPanel searchPanel;
    private OptionPanel optionPanel;
    private ProgressPanel progressPanel;

    //Menu Items



    //


    public void displayGraph(){
        JFrame frame = new JFrame("Card Layout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x=e.getX();
                int y=e.getY();
                System.out.println("x="+x+" ,y="+y);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        JPanel contentPane = new JPanel();
        contentPane.setBorder(
                BorderFactory.createEmptyBorder(10, 15, 15, 15));
        contentPane.setLayout(new CardLayout());
        initialPanel = new InitialPanel(contentPane);
        graphpanel = new GraphPanel(contentPane);
        logpanel=new LogPanel(contentPane);
        nodelogpanel= new NodeLogPanel(contentPane);
        searchPanel=new SearchPanel(contentPane);
        optionPanel= new OptionPanel(contentPane);
        progressPanel= new ProgressPanel(contentPane);
        contentPane.add(initialPanel, "initialPanel");
        contentPane.add(graphpanel, "graphpanel");
        contentPane.add(logpanel, "logpanel");
        contentPane.add(nodelogpanel, "nodelogpanel");
        contentPane.add(searchPanel, "searchPanel");
        contentPane.add(optionPanel, "optionPanel");

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
