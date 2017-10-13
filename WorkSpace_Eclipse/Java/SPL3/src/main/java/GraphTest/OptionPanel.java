package GraphTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionPanel extends JPanel implements ActionListener {
    private JPanel contentPane;
    private JButton button_logPanel;
    public OptionPanel(JPanel panel) {

        contentPane = panel;

        button_logPanel= new JButton("Log Tuples");
        button_logPanel.setLocation(90,100);
        button_logPanel.setSize(90,30);

        button_logPanel.addActionListener(this);

        add(button_logPanel);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == button_logPanel) {
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane,"logpanel");

        }
    }
}