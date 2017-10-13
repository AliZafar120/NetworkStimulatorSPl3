package GraphTest;

import FinalRapidnetOutputAnalyis.LogFormat;
import javafx.scene.layout.GridPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LogPanel extends ScrollPane {
    private JPanel contentPane;
    private String chosen;


    public LogPanel(JPanel panel) {
        contentPane = panel;

        setPreferredSize (new Dimension(500, 500));

        ArrayList<String>a= new ArrayList<String>();
        a.add("ABC");
        a.add("CDF");
        a.add("EFG");
        a.add("AIJ");

        getLogs(a);
    }

    public void getLogs( ArrayList<String> logs){
        JPanel apanel=new JPanel();
        apanel.setLayout(new GridLayout(this.getWidth(),30));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x,y;
        x=dim.width-45;
        y=0;

        for(final String log:logs){
            JPanel panel= new JPanel();
            panel.setSize(dim.width,30);
            final JButton button= new JButton(log);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   // button.setBackground(Color.BLUE);
                    if(chosen!=null) {
                        button.setBackground(Color.WHITE);
                        chosen=null;
                    }else{
                        chosen=log;
                        button.setBackground(Color.BLUE);
                    }
                }
            });
            button.setSize(this.getWidth()-100,30);

            panel.add(button);
            apanel.add(panel);
        }

        add(apanel);

    }


}
