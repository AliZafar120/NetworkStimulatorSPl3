package GraphTest;

import FinalRapidnetOutputAnalyis.Events.*;
import FinalRapidnetOutputAnalyis.Events.Event;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphPanel extends ScrollPane{
    private JPanel contentPane;
    public Event originevent= new Event();
    public Event currentEvent = new Event();
    public JButton button_back;
    String from="";
    int vertices_in_response=0;
    public GraphPanel(final JPanel contentPane){
        this.contentPane=contentPane;
        button_back= new JButton("Back");
        JPanel panel_option= new JPanel();
        button_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) contentPane.getLayout();

                if(from.compareTo("log")==0) cardLayout.show(contentPane,"logpanel");
                else cardLayout.show(contentPane,"searchPanel");
            }
        });


        panel_option.add(button_back);
        add(panel_option);

        /*
        this.originevent.eventName="The Parent Event";
        this.originevent.childs.add(new Event("This is the first child"));
        this.originevent.childs.add(new Event("This is the Second child"));
        this.originevent.childs.add(new Event("This is the Third child"));
*/
        //drawGraph();
    }

    public void setOriginEvent(Event originevent) {
        this.originevent=originevent;

    }

    public void drawGraph(){

        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();
        graph.getModel().beginUpdate();
        try
        {

            setLocation(graph,null,originevent,null,0,0,0,0,parent);


/*

                Object v1 = graph.insertVertex(parent, null, "Hello", 20, 20, 10,
                        10);
                //x,y,width,height

                Object v2 = graph.insertVertex(parent, null, "World!", 240, 150,
                        80, 30);
                System.out.println(graph.getModel().getGeometry(v1).getX());
                graph.insertEdge(parent, null, "Edge", v1, v2);*/

        }
        finally
        {
            graph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        this.add(graphComponent);
    }

    public void setLocation(mxGraph graph,Event parent,Event current,Object parentobject,double parent_x, double parent_y, int parentchilds,int childno,Object defaultparent){

            if(parent==null){
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int centerX = screenSize.width/2;
                String eventname=getEventDetails(current);
                vertices_in_response++;
                        //current.getEventName()+" "+current.getTime()+" "+currentEvent.node;
                Object v1 = graph.insertVertex(defaultparent, null, eventname, centerX-100,0,200, 200 );
                if(current.childs!=null) {
                    int i=0;
                    for (Event child : current.childs) {
                        setLocation(graph, current, child, v1, graph.getModel().getGeometry(v1).getX()+graph.getModel().getGeometry(v1).getWidth()/2, graph.getModel().getGeometry(v1).getY(), current.childs.size(),i, defaultparent);
                        i++;
                    }
                }

            }
            else{
                String eventname=getEventDetails(current);
                vertices_in_response++;
                //+" "+current.getTime()+" "+currentEvent.node
                Object v2= graph.insertVertex(defaultparent, null, eventname, parent_x-parentchilds*250/2+childno*250,parent_y+300, 200, 200);
                graph.insertEdge(defaultparent, null, "",v2, parentobject);
                if(current.childs!=null) {
                    int i=0;
                    for (Event child : current.childs) {
                        setLocation(graph, current, child, v2, graph.getModel().getGeometry(v2).getX()+graph.getModel().getGeometry(v2).getWidth()/2, graph.getModel().getGeometry(v2).getY(), current.childs.size(), i,defaultparent);
                        i++;
                    }

                }

            }

    }

    public String getEventDetails(Event event){
        if(event.eventName.compareToIgnoreCase("Exist")==0){
            return ((ExistEvent)event).toString();
        }
        else if(event.eventName.compareToIgnoreCase("Appear")==0){
            return ((AppearEvent)event).toString();
        }
        else if(event.eventName.compareToIgnoreCase("Delay")==0){
            return ((DelayEvent)event).toString();
        }
        else if(event.eventName.compareToIgnoreCase("Delete")==0){
            return ((DeleteEvent)event).toString();
        }
        else if(event.eventName.compareToIgnoreCase("Derive")==0){
            return ((DeriveEvent)event).toString();
        }
        else if(event.eventName.compareToIgnoreCase("Dissapear")==0){
            return ((DissapearEvent)event).toString();
        }
        else if(event.eventName.compareToIgnoreCase("Inserted")==0){
            return ((InsertEvent)event).toString();
        }
        else if(event.eventName.compareToIgnoreCase("NAppear")==0){
            return ((NAppearEvent)event).toString();
        }
        else if(event.eventName.compareToIgnoreCase("NArrive")==0){
            return ((NArriveEvent)event).toString();
        }
        else if(event.eventName.compareToIgnoreCase("NDerive")==0){
            return ((NDeriveEvent)event).toString();
        }
        else if(event.eventName.compareToIgnoreCase("NExist")==0){
            return ((NExistEvent)event).toString();
        }
        else if(event.eventName.compareToIgnoreCase("NInserted")==0){
            return ((NInsertEvent)event).toString();
        }
        else if(event.eventName.compareToIgnoreCase("NReceive")==0){
            return ((NReceiveEvent)event).toString();
        }
        else if(event.eventName.compareToIgnoreCase("NSend")==0){
            return ((NSendEvent)event).toString();
        }


        else if(event.eventName.compareToIgnoreCase("Received")==0){
            return ((ReceiveEvent)event).toString();
        }


        else if(event.eventName.compareToIgnoreCase("Send")==0){
            return ((SendEvent)event).toString();
        }

        else if(event.eventName.compareToIgnoreCase("Underive")==0){
            return ((UnderiveEvent)event).toString();
        }



        return "";
    }

    public String getVerticesInResponse(){
        return vertices_in_response+"";
    }


}
