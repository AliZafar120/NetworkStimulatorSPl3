package GraphTest;

import FinalRapidnetOutputAnalyis.Parser.ApplicationLogParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class Graph implements ActionListener{
    private JPanel contentPane;
    public static InitialPanel initialPanel;
    public static GraphPanel graphpanel;
    public static LogPanel logpanel;
    public static NodeLogPanel nodelogpanel;
    public static SearchPanel searchPanel;
    public static OptionPanel optionPanel;
    public ProgressPanel progressPanel;


    public static boolean isChoiceSuper;

    //Menu Items
    public JMenuBar menubar;

    public JMenu view_jmenu;

    public JMenuItem optionPanel_jmenuitem;
    public JMenuItem filePanel_jmenuitem;


    public JMenu choice_jmenu;

    public JMenuItem allvertices_jmenuitem;
    public JMenuItem supervertices_jmenuitem;



    //


    public void displayGraph(){
        JFrame frame = new JFrame("Provenance");
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

        contentPane= new JPanel();
        contentPane.setBorder(
                BorderFactory.createEmptyBorder(10, 15, 15, 15));
        contentPane.setLayout(new CardLayout());
        initialPanel = InitialPanel.getInstance(contentPane);
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


        menubar= new JMenuBar();
        view_jmenu= new JMenu("View");

        optionPanel_jmenuitem= new JMenuItem("Options");
        filePanel_jmenuitem= new JMenuItem("File Choice");

        optionPanel_jmenuitem.addActionListener(this);
        filePanel_jmenuitem.addActionListener(this);

        view_jmenu.add(optionPanel_jmenuitem);
        view_jmenu.add(filePanel_jmenuitem);




        choice_jmenu= new JMenu("Graph Choice");

        allvertices_jmenuitem= new JMenuItem("All");
        supervertices_jmenuitem= new JMenuItem("Super");

        allvertices_jmenuitem.addActionListener(this);
        supervertices_jmenuitem.addActionListener(this);

        choice_jmenu.add(allvertices_jmenuitem);
        choice_jmenu.add(supervertices_jmenuitem);


        menubar.add(view_jmenu);
        menubar.add(choice_jmenu);

        frame.setJMenuBar(menubar);
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

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==filePanel_jmenuitem){
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane,"initialPanel");

        }

        if(e.getSource()==optionPanel_jmenuitem){
            if(Graph.initialPanel.fileString!=null && Graph.initialPanel.fileString.compareTo("")!=0 &&(new File(Graph.initialPanel.fileString)).isFile()){
                ApplicationLogParser parse= new ApplicationLogParser();
                parse.setLogFilePath(Graph.initialPanel.fileString);
                parse.setLogFilebuffer();
                try {
                    Graph.initialPanel.rapidnetLogs=parse.getAllFormattedLog(parse.parseRapidnetLog());
                } catch (CloneNotSupportedException ex) {
                    ex.printStackTrace();
                }
                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.show(contentPane,"optionPanel");

                /*formattedlogs= parse.getAllFormattedLog( parse.parseRapidnetLog());
                        contentPane.ge*/
            }
        }
        if(e.getSource()==allvertices_jmenuitem){
            JOptionPane.showMessageDialog(null, "Choice 'all vertices' is selected");
            isChoiceSuper=false;
        }

        if(e.getSource()==supervertices_jmenuitem){
            JOptionPane.showMessageDialog(null, "Choice 'super vertices' is selected");
            isChoiceSuper=true;
        }

    }
}
