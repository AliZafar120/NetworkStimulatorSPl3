package GraphTest;

import FinalRapidnetOutputAnalyis.LogFormat;
import Queries.TupleQuery;
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

      /*  ArrayList<String>a= new ArrayList<String>();
        a.add("ABC");
        a.add("CDF");
        a.add("EFG");
        a.add("AIJ");

        getLogs(a);*/
    }

    public void setLogs(final ArrayList<LogFormat> logs){
        JPanel apanel=new JPanel();
        apanel.setLayout(new GridLayout(this.getWidth(),30));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x,y;
        x=dim.width-45;
        y=0;

        for(final LogFormat log:logs){
            JPanel panel= new JPanel();
            panel.setSize(dim.width,30);
            final JButton button= new JButton(log.t.toString());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   // button.setBackground(Color.BLUE);
                    TupleQuery query1= new TupleQuery();
                    query1.setLogs(logs);
                    query1.searchTuple(log.t,"exist",log.node,"0","10000000000");
                    Graph.graphpanel.setOriginEvent(query1.origin);
                    Graph.graphpanel.drawGraph();
                    CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                    cardLayout.show(contentPane,"graphpanel");
                }
            });
            button.setSize(this.getWidth()-100,30);

            panel.add(button);
            apanel.add(panel);
        }

        add(apanel);

    }


}
