package application.Controllers;
import application.IdLists;
import application.PizzaLists;
import application.Status;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import application.NodeData;
import javafx.scene.control.ScrollPane;

public class AgentScreenController extends SceneController {
    @FXML
    private Button AgentGetOrdersButton;

    public void newToReadyList(){
        //Call upon newOrder confirm button
        //Iterate through the newList
        for (Iterator<NodeData> iterator = PizzaLists.getList("newList").iterator(); iterator.hasNext();) {
            NodeData curr = iterator.next();
            for (int j = 0; j < IdLists.getIdLists("AgentnewList").size(); j++){
                //Check if the ids in AgentNewlist and newList match and add them to the readyList
                if (curr.getId() == IdLists.getIdLists("AgentnewList").get(j)[0]); {
                    curr.setStatus(Status.READY);
                    PizzaLists.getList("readyList").add(curr);
                    //Remove checked boxes from the pane
                    int[] cbId = new int[1];
                    cbId[0] = (IdLists.getIdLists("AgentnewList").get(j)[1]);
                    IdLists.getIdLists("AgentnewList").remove(j);

                    for (int i = 0; i < AgentNewOrdersVB.getChildren().size(); i++) {
                        if (Integer.toString(cbId[0]).equals(AgentNewOrdersVB.getChildren().get(i).getId())) {
                            AgentNewOrdersVB.getChildren().remove(i);
                        }
                    }
                    iterator.remove();
                    System.out.println(curr);
                    break;
                }
            }
        }
        System.out.println("NEWLIST: " + PizzaLists.getList("newList").toString() + " **Removed from");
        System.out.println("READYLIST: " + PizzaLists.getList("readyList").toString() + "**Added to");
    }

    public void newToRejectedList() {
        //Call upon newOrder reject button
        //Iterate through the new list
        for (Iterator<NodeData> iterator = PizzaLists.getList("newList").iterator(); iterator.hasNext(); ) {
            NodeData curr = iterator.next();
            for (int j = 0; j < IdLists.getIdLists("AgentnewList").size(); j++) {
                //Check if the ids in AgentNewlist and newList match and add them to the readyList
                if (curr.getId() == IdLists.getIdLists("AgentnewList").get(j)[0]) ;
                {
                    curr.setStatus(Status.REJECT);
                    PizzaLists.getList("rejectList").add(curr);
                    //Remove checked boxes from the pane
                    int[] cbId = new int[1];
                    cbId[0] = (IdLists.getIdLists("AgentnewList").get(j)[1]);
                    IdLists.getIdLists("AgentnewList").remove(j);

                    for (int i = 0; i < AgentNewOrdersVB.getChildren().size(); i++) {
                        if (Integer.toString(cbId[0]).equals(AgentNewOrdersVB.getChildren().get(i).getId())) {
                            AgentNewOrdersVB.getChildren().remove(i);
                        }
                    }
                    iterator.remove();
                    System.out.println(curr);
                    break;
                }
            }
        }
        System.out.println("REJECTEDLIST: " + PizzaLists.getList("rejectedList").toString() + "**Added to");
        System.out.println("NEWLIST: " + PizzaLists.getList("newList").toString() + "**Removed from");
    }

    public void AgentGetInfo(){
        AgentGetOrdersButton.setDisable(true);
        //New Orders
        createCheckBox(PizzaLists.getList("newList"), AgentNewOrdersVB);

        //Finished Orders
        createCheckBox(PizzaLists.getList("finishedList"), AgentFinishedOrdersVB);
    }
    public void finishedListPickedUp(){
        //Call upon finishedOrder [Confirmed Picked-Up] button
        //When a finished order is selected and Picked-Up, order completed, remove it from finished list.
        for (Iterator<NodeData> iterator = PizzaLists.getList("finishedList").iterator(); iterator.hasNext(); ) {
            NodeData curr = iterator.next();
            for (int j = 0; j < IdLists.getIdLists("AgentfinishedList").size(); j++) {
                //Check if the ids in Agentfinishedlist and finishedList match and add them to the readyList
                if (curr.getId() == IdLists.getIdLists("AgentfinishedList").get(j)[0]) ;
                {
                    PizzaLists.getList("readyList").add(curr);
                    //Remove checked boxes from the pane
                    int[] cbId = new int[1];
                    cbId[0] = (IdLists.getIdLists("AgentfinishedList").get(j)[1]);
                    IdLists.getIdLists("AgentfinishedList").remove(j);

                    for (int i = 0; i < AgentFinishedOrdersVB.getChildren().size(); i++) {
                        if (Integer.toString(cbId[0]).equals(AgentFinishedOrdersVB.getChildren().get(i).getId())) {
                            AgentFinishedOrdersVB.getChildren().remove(i);
                        }
                    }

                    for (Iterator<NodeData> iterator2 = PizzaLists.getList("allNodesList").iterator(); iterator2.hasNext(); ) {
                        NodeData curr2 = iterator2.next();
                        if(curr2.equals(curr)){
                            iterator2.remove();
                        }
                    }

                    iterator.remove();
                    System.out.println(curr);
                    break;
                }
            }
        }
        System.out.println("FINISHEDLIST: " + PizzaLists.getList("finishedList").toString());
    }

}
