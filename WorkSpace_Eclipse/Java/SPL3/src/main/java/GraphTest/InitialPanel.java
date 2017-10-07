package GraphTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InitialPanel extends JPanel {

    private JTextField How;
    private JLabel jcomp2;
    private JLabel jcomp3;
    private JButton jcomp4;
    private JPanel contentPane;

    public InitialPanel(JPanel panel) {

        contentPane = panel;
        //construct components
        How = new JTextField (1);
        jcomp2 = new JLabel ("How long were you parked?");
        jcomp3 = new JLabel ("Minutes");
        jcomp4 = new JButton ("openNewWindow");

        //adjust size and set layout
        setPreferredSize (new Dimension(315, 85));
        setLayout (null);

        //set component bounds (only needed by Absolute Positioning)
        How.setBounds (245, 50, 60, 25);
        jcomp2.setBounds (35, 30, 185, 50);
        jcomp3.setBounds (250, 30, 60, 20);
        jcomp4.setLocation(0, 0);
        jcomp4.setSize(315, 25);
        jcomp4.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.next(contentPane);
            }
        });

        //add components
        add (How);
        add (jcomp2);
        add (jcomp3);
        add (jcomp4);
    }
}