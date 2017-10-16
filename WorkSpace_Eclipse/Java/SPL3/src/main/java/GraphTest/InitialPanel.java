package GraphTest;

import FinalRapidnetOutputAnalyis.ApplicationLogParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class InitialPanel extends JPanel implements ActionListener{

    private JLabel label_upload_file;
    private JLabel label_selected_file_folder;
    private JButton button_next;
    private JButton button_select;
    JFileChooser fileChooser = new JFileChooser();
    String fileString = "";
    private JPanel contentPane;

    public InitialPanel(JPanel panel) {

        contentPane = panel;
        //construct components
        label_upload_file = new JLabel ("Please upload a System Log file or select Pcap folder");
        label_selected_file_folder=new JLabel ("No file/folder selected");
        button_select= new JButton("Select");
        button_next= new JButton("Next");
        /*jcomp3 = new JLabel ("Minutes");
        jcomp4 = new JButton ("openNewWindow");
*/
        //adjust size and set layout
        setPreferredSize (new Dimension(315, 85));
        setLayout (null);

        //set component bounds (only needed by Absolute Positioning)


        label_upload_file.setBounds (50, 30, 500, 50);
        label_selected_file_folder.setBounds (50, 100, 500, 50);
        button_select.setLocation(90,200);
        button_select.setSize(90,30);
        button_next.setLocation(210,200);
        button_next.setSize(90,30);
   /*      jcomp3.setBounds (250, 30, 60, 20);
        jcomp4.setLocation(0, 0);
        jcomp4.setSize(315, 25);
        jcomp4.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.next(contentPane);

            }
        });*/

        //add buttons to listeners
        button_select.addActionListener(this);
        button_next.addActionListener(this);

        //added items to jlabel
        add (label_upload_file);
        add (label_selected_file_folder);
        add(button_select);
        add(button_next);
       /* add (jcomp3);
        add (jcomp4);*/
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if(actionEvent.getSource() == button_select) {
            //select file
            if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){

                //get the file
                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                java.io.File file = fileChooser.getSelectedFile();
                if(file.isFile()){
                    label_selected_file_folder.setText("File "+file.getName()+" Selected");
                    fileString=file.getAbsolutePath();
                }
                if(file.isDirectory()){
                    label_selected_file_folder.setText("Folder "+file.getName()+" Selected");
                    fileString=file.getAbsolutePath();
                }

                  update(getGraphics());
            }

        }

        if(actionEvent.getSource() == button_next) {

            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane,"optionPanel");
            //for system log
            if(fileString.compareTo("")!=0 &&(new File(fileString)).isFile()){
                ApplicationLogParser parse= new ApplicationLogParser();
                parse.setLogFilePath(fileString);
                parse.setLogFilebuffer();

                /*formattedlogs= parse.getAllFormattedLog( parse.parseRapidnetLog());
                        contentPane.ge*/
            }
            //for pcapfiles


        }


    }
}