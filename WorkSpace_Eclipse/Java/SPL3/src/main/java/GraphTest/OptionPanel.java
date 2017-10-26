package GraphTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OptionPanel extends JPanel implements ActionListener {
    private JPanel contentPane;
    private JButton button_logPanel;
    private JButton button_queryTuple;
    private JButton button_back;
    private JPanel panel_button;

    public OptionPanel(JPanel panel) {
        contentPane = panel;

        panel_button= new JPanel();
        BoxLayout boxLayout1 = new BoxLayout(panel_button, BoxLayout.Y_AXIS);
        panel_button.setLayout(boxLayout1);
        panel_button.add(Box.createVerticalGlue());

        button_logPanel= new JButton("Log Tuples");
        button_logPanel.addActionListener(this);
        button_logPanel.setSize(90,30);

        button_queryTuple= new JButton("Query Tuple");
        button_queryTuple.setSize(90,30);
        button_queryTuple.addActionListener(this);


        button_back= new JButton("Back");
        button_back.setSize(90,30);
        button_back.addActionListener(this);

        panel_button.add(button_logPanel);
        panel_button.add(Box.createRigidArea(new Dimension(0,25)));
        panel_button.add(button_queryTuple);
        panel_button.add(Box.createRigidArea(new Dimension(0,25)));
        panel_button.add(button_back);

        add(panel_button);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == button_logPanel) {
            Graph.logpanel.setLogs( Graph.initialPanel.getLogFormat());
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane,"logpanel");

        }

        if (actionEvent.getSource() == button_queryTuple) {
           //Query option
            //after changing class queries

        }

        if (actionEvent.getSource() == button_back) {
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane,"initialPanel");
        }

    }
}