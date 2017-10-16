package GraphTest;

import javax.swing.*;
import java.awt.*;

public class ProgressPanel extends JPanel{
    JProgressBar progressbar_progress;
    private JPanel contentPane;
    private JLabel Jlabel;
    static final int MY_MINIMUM = 0;

    static final int MY_MAXIMUM = 100;
    public void updateBar(int newValue) {
        progressbar_progress.setValue(newValue);
    }
    public void nextPanel(String nextpanel) {
        CardLayout cardLayout = (CardLayout) contentPane.getLayout();
        cardLayout.show(contentPane,"nextpanel");

    }
    public ProgressPanel(JPanel panel) {
        // initialize Progress Bar
        contentPane=panel;
        progressbar_progress = new JProgressBar();
        progressbar_progress.setMinimum(MY_MINIMUM);
        progressbar_progress.setMaximum(MY_MAXIMUM);
        // add to JPanel
        progressbar_progress.setLocation(250,250);
        add(progressbar_progress);
    }
}
