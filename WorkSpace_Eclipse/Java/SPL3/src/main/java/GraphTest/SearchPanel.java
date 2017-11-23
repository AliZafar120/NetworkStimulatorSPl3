package GraphTest;

import FinalRapidnetOutputAnalyis.Tuples.Tuple;
import Queries.TupleQuery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class SearchPanel extends ScrollPane implements ActionListener{
    private JPanel contentPane;

    private Tuple tupleStructure;
    private JLabel label_tuple_name;
    private JLabel label_search_result;
    private JTextField textfield_tuple_name;
    private JButton button_search;
    private HashMap<Integer,JTextField> attrTextFields;
    private HashMap<Integer,ArrayList<JTextField>>arrayAttr;
    private JLabel label_selected_file_folder;

    private JPanel panel_Search;

    public SearchPanel(JPanel panel) {

        contentPane = panel;
        panel_Search= new JPanel();
        GridBagLayout layout = new GridBagLayout();

        panel_Search.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();


        label_search_result= new JLabel("Please Enter Tuple name and click Search");
        label_tuple_name= new JLabel("Tuple Name");
        textfield_tuple_name= new JFormattedTextField();
        button_search= new JButton("Search");
        button_search.addActionListener(this);


        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel_Search.add(label_tuple_name,gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(0,10,0,0);
        panel_Search.add(textfield_tuple_name,gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20,0,0,0);
        panel_Search.add(button_search,gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;

        gbc.insets = new Insets(20,0,0,0);

        panel_Search.add(label_search_result,gbc);
        add(panel_Search);
    }



    private void searchResultText(String text) {
        label_search_result.setText(text);
    }



    @Override
    public void actionPerformed(ActionEvent event) {
        TupleQuery query= new TupleQuery();
        query.setLogs(Graph.initialPanel.getLogFormat());
        if(event.getSource()==button_search){
            tupleStructure= query.getTupleStructure(textfield_tuple_name.getText());
            if(tupleStructure==null){
                searchResultText("No such type of Tuples are found. Please Retry.");
            }
            else {
                attrTextFields= new HashMap<Integer, JTextField>();
                if(tupleStructure.hasArrayAttribute()){
                    arrayAttr= new HashMap<Integer, ArrayList<JTextField>>();
                }
                setLayoutForTupleInformation();
            }
        }
    }


    public void setLayoutForTupleInformation(){
        final JPanel tuplePanel= new JPanel();
        GridBagLayout layout = new GridBagLayout();

        tuplePanel.setLayout(layout);
        final GridBagConstraints gbc = new GridBagConstraints();

        int j=0;
        for(int i=1;i<= tupleStructure.attributes.size();i++){

            if(tupleStructure.attributes.get(i).islist)
            {
                ArrayList<JTextField> arraylistTextFields= new ArrayList<JTextField>();
                JLabel attrLable = new JLabel("Attribute " + i + ": " + tupleStructure.attributes.get(i).tupleAttributeName);
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.gridx = 0;
                gbc.gridy = j;
                tuplePanel.add(attrLable, gbc);
                JTextField attrtextfield= new JTextField();
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.gridx = 1;
                gbc.gridy = j;
                tuplePanel.add(attrtextfield, gbc);
                arraylistTextFields.add(attrtextfield);
                j++;
                JButton button_AddArraylist = new JButton("Add");
                button_AddArraylist.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JTextField attrtextfield= new JTextField();
                        gbc.fill = GridBagConstraints.HORIZONTAL;
                        gbc.gridx = 1;
                        //gbc.gridy = j;
                        tuplePanel.add(attrtextfield, gbc);
                        //j++;
                    }
                });
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.gridx = 2;
                gbc.gridy = j;
                tuplePanel.add(button_AddArraylist, gbc);




            }else {
                JLabel attrLable = new JLabel("Attribute " + i + ": " + tupleStructure.attributes.get(i).tupleAttributeName);
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.gridx = 0;
                gbc.gridy = j;
                tuplePanel.add(attrLable, gbc);
                gbc.gridx = 1;
                gbc.gridy = j;
                JTextField attrfield= new JTextField();
                attrTextFields.put(i,attrfield);
                tuplePanel.add(attrfield);
            }
            j++;

        }

        add(tuplePanel);

    }

}
