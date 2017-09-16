package GraphTest;

import javax.swing.*;
import java.awt.*;

public class Test extends JFrame {

    private JLabel label1;
    private JButton button1;
    private JTextField textfield1;
    private javax.swing.JMenuBar jmenubar;
    private JMenu start;
    private JMenu help;
    private JMenuItem rapidnetApplications;
    private JMenuItem packetTracers;

    private JPanel panel1;
    private JPanel panel2;
    private JTextField textfield2;



    public Test(){
        setLayout(new FlowLayout());
        this.setSize(500,500);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        panel1= new JPanel();

        label1= new JLabel("A label");
        textfield1= new JFormattedTextField("A textfield");
        button1= new JButton("A button");
        jmenubar= new JMenuBar();
        help= new JMenu("Help");
        start= new JMenu("Start");

        rapidnetApplications= new JMenuItem("RapidnetApplications");
        packetTracers= new JMenuItem("PacketTracers");

        start.add(rapidnetApplications);
        start.add(packetTracers);

        jmenubar.add(start);
        jmenubar.add(help);




        this.setJMenuBar(jmenubar);

        panel1.add(label1);
        panel1.add(textfield1);
        panel1.add(button1);
        this.add(panel1);

        panel2= new JPanel();
        textfield2= new JTextField("panel 2");
        panel2.add(textfield2);

        rapidnetApplications.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            changePanel(panel2);
                repaint();
            }
        });

    }

    private void changePanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel, new FlowLayout());
        getContentPane().doLayout();
        update(getGraphics());
        setVisible(true);
    }


    public static void main(String[] args) {
        Test gui= new Test();

        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gui.setVisible(true);
        gui.setTitle("Initial");

    }

}
