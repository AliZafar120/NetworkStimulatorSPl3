package GraphTest;

import FinalRapidnetOutputAnalyis.Tuples.Tuple;
import Queries.TupleQuery;
import com.sun.org.apache.xpath.internal.SourceTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SearchPanel extends ScrollPane implements ActionListener{
    private JPanel contentPane;

    private Tuple tupleStructure;
    private JLabel label_tuple_name;
    private JLabel label_search_result;
    private JTextField textfield_tuple_name;
    private JButton button_search;
    private HashMap<Integer,JTextField> attrTextFields;
    private JLabel label_selected_file_folder;
    private JButton button_AddArraylist=null;
    JTextField numberofAttr;
    JLabel attrLable;
    private JPanel panel_container;
    private JPanel panel_search;
    private JPanel panel_tuple;
    JComboBox queryType;
    int heightPosition=0;
    private JButton button_search_tuple;

    private JTextField textfield_node_name;

    private JTextField textfield_start_time;

    private JTextField textfield_end_time;

    GridBagConstraints main_gbc;

    public SearchPanel(JPanel panel) {

        contentPane = panel;
        panel_search= new JPanel();
        panel_container= new JPanel();
        panel_container.setLayout(new GridBagLayout());

        main_gbc= new GridBagConstraints();

        GridBagLayout layout = new GridBagLayout();
        panel_search.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();


        label_search_result= new JLabel("Please Enter Tuple name and click Search");
        label_tuple_name= new JLabel("Tuple Name");
        textfield_tuple_name= new JFormattedTextField();
        button_search= new JButton("Search");
        button_search.addActionListener(this);


        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = heightPosition;
        panel_search.add(label_tuple_name,gbc);
        gbc.gridx = 1;
        gbc.gridy = heightPosition++;
        gbc.insets = new Insets(0,10,0,0);
        panel_search.add(textfield_tuple_name,gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = heightPosition++;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20,0,0,0);
        panel_search.add(button_search,gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = heightPosition++;
        gbc.gridwidth = 2;

        gbc.insets = new Insets(20,0,0,0);

        panel_search.add(label_search_result,gbc);

        main_gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        main_gbc.fill = GridBagConstraints.BOTH;
        main_gbc.gridx = 0;
        main_gbc.gridy = 0;
        main_gbc.weightx = 1.0;
        main_gbc.weighty = 0.3;

        panel_container.add(panel_search,main_gbc);
        add(panel_container);
    }



    private void searchResultText(String text) {
        label_search_result.setText(text);
    }



    @Override
    public void actionPerformed(ActionEvent event) {
        TupleQuery query= new TupleQuery();
        query.setLogs(Graph.initialPanel.getLogFormat());
        if(event.getSource()==button_search){
            if(panel_tuple!=null)panel_container.remove(panel_tuple);
            tupleStructure= query.getTupleStructure(textfield_tuple_name.getText());

            if(tupleStructure==null){
                searchResultText("No such type of Tuples are found. Please Retry.");
                panel_container.revalidate();
            }
            else {
                attrTextFields= new HashMap<Integer, JTextField>();
                setLayoutForTupleInformation();
            }
        }

       if(event.getSource()==button_search_tuple){
            String queryOption=queryType.getSelectedItem().toString();
            for(int i=0;i< tupleStructure.attributes.size();i++){

                if(tupleStructure.attributes.get(i).islist){
                    tupleStructure.attributes.get(i).tupleAttributelistValue= new ArrayList<String>(Arrays.asList(attrTextFields.get(i).getText().toString().replaceAll("\\s","").split(",")));
                }
                else{
                    tupleStructure.attributes.get(i).tupleAttributeValue=attrTextFields.get(i).getText().toString().replaceAll("\\s","");
                }

            }
           query.searchTuple(tupleStructure,queryOption,textfield_node_name.getText().toString().replaceAll("\\s",""),textfield_start_time.getText().toString().replaceAll("\\s","")+"000000000",textfield_end_time.getText().toString().replaceAll("\\s","")+"000000000");

           Graph.graphpanel.setOriginEvent(query.origin);
           Graph.graphpanel.drawGraph();
           CardLayout cardLayout = (CardLayout) contentPane.getLayout();
           cardLayout.show(contentPane,"graphpanel");


           //query.gett
       }

    }


    public void setLayoutForTupleInformation(){
        panel_tuple= new JPanel();
        GridBagLayout layout = new GridBagLayout();

        panel_tuple.setLayout(layout);
        final GridBagConstraints gbc = new GridBagConstraints();
        int height=heightPosition;
        for(int i=0;i< tupleStructure.attributes.size();i++){


                if(tupleStructure.attributes.get(i).islist) {
                    JLabel attrLable = new JLabel("Attribute " + (i+1) + ": " + tupleStructure.attributes.get(i).tupleAttributeName+ "(*Array, enter values in comma separated format*)");
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    gbc.gridx = 0;
                    gbc.gridy = height++;
                    panel_tuple.add(attrLable, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = height++;
                    gbc.gridwidth=2;
                    JTextField attrfield = new JTextField();
                    attrTextFields.put(i, attrfield);
                    panel_tuple.add(attrfield, gbc);
                    gbc.gridwidth=0;
                }
                else{
                    JLabel attrLable = new JLabel("Attribute " + (i+1) + ": " + tupleStructure.attributes.get(i).tupleAttributeName);
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    gbc.gridx = 0;
                    gbc.gridy = height++;
                    panel_tuple.add(attrLable, gbc);

                    gbc.gridx = 0;
                    gbc.gridy = height++;
                    gbc.gridwidth=2;
                    JTextField attrfield = new JTextField();
                    attrTextFields.put(i, attrfield);
                    panel_tuple.add(attrfield, gbc);
                    gbc.gridwidth=1;
                }


        }

        JLabel lable_qtype = new JLabel("Query Type");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = height++;
        gbc.insets = new Insets(20,0,0,0);
        panel_tuple.add(lable_qtype, gbc);

        gbc.gridx = 0;
        gbc.gridy = height++;
        gbc.gridwidth=1;
        gbc.insets = new Insets(0,0,0,0);
        queryType= new JComboBox(new String[]{"Exit", "NExist"});
        queryType.setSelectedIndex(0);
        panel_tuple.add(queryType, gbc);


        JLabel node_lable = new JLabel("Query at Node");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = height++;
        gbc.insets = new Insets(0,0,0,0);
        panel_tuple.add(node_lable, gbc);

        gbc.gridx = 0;
        gbc.gridy = height++;
        gbc.gridwidth=2;
        textfield_node_name = new JTextField();
        gbc.insets = new Insets(10,0,0,0);
        panel_tuple.add(textfield_node_name, gbc);
        gbc.gridwidth=1;


        JLabel lable_stime = new JLabel("Start Time");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = height++;
        gbc.insets = new Insets(0,0,0,0);
        panel_tuple.add(lable_stime, gbc);

        gbc.gridx = 0;
        gbc.gridy = height++;
        gbc.gridwidth=2;
        textfield_start_time = new JTextField();
        gbc.insets = new Insets(10,0,0,0);
        panel_tuple.add(textfield_start_time, gbc);
        gbc.gridwidth=1;



        JLabel lable_ftime = new JLabel("Time end");
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = height++;
        gbc.insets = new Insets(0,0,0,0);
        panel_tuple.add(lable_ftime, gbc);

        gbc.gridx = 0;
        gbc.gridy = height++;
        gbc.gridwidth=2;
        textfield_end_time = new JTextField();
        gbc.insets = new Insets(10,0,0,0);
        panel_tuple.add(textfield_end_time, gbc);
        gbc.gridwidth=1;



        button_search_tuple= new JButton("Search tuple");
        gbc.gridx = 0;
        gbc.gridy = height++;
        gbc.gridwidth=2;
        gbc.insets = new Insets(20,0,0,0);
        button_search_tuple.addActionListener(this);
        panel_tuple.add(button_search_tuple, gbc);



        main_gbc.weighty = 0.7;
        main_gbc.gridy = 1;
        panel_container.add(panel_tuple,main_gbc);
        add(panel_container);
    }

   /* public void setArrayItems(){
        String input=numberofAttr.getText();
        int numberofAttributes=0;
        if (input.contains("[a-zA-Z]+") == false) {
            numberofAttributes = Integer.parseInt(input);
        }else return;

        for(int i=1;i<=numberofAttributes;i++){

            JTextField attrtextfield= new JTextField();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 1;
            //gbc.gridy = j;
            tuplePanel.add(attrtextfield, gbc);
            //j++;

        }


    }
*/
}
