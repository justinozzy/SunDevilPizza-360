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
        int count = 0;
        //Iterate through the newList
        for (Iterator<NodeData> iterator = PizzaLists.getList("newList").iterator(); iterator.hasNext();) {
            NodeData curr = iterator.next();
            for (int j = 0; j < IdLists.getIdLists("AgentnewList").size(); j++){
                //Check if the ids in AgentNewlist and newList match and add them to the readyList
                if (curr.getId() == IdLists.getIdLists("AgentnewList").get(j)[0]); {
                    curr.setStatus(Status.READY);
                    PizzaLists.getList("readyList").add(curr);
                    iterator.remove();
                    //Remove checked boxes from the pane
                    int cbId = IdLists.getIdLists("AgentnewList").get(count)[1];
                    AgentNewOrdersVB.getChildren().remove(cbId);
                }
                System.out.println(curr);
            }
            count++;
        }
        System.out.println("NEWLIST: " + PizzaLists.getList("newList").toString() + " **Removed from");
        System.out.println("READYLIST: " + PizzaLists.getList("readyList").toString() + "**Added to");
    }

    public void newToRejectedList(){
        //Call upon newOrder reject button
        int count = 0;
        //Iterate through the new list
        for (Iterator<NodeData> iterator = PizzaLists.getList("newList").iterator(); iterator.hasNext();) {
            NodeData curr = iterator.next();
            //Check if the ids match
            for (int j = 0; j < IdLists.getIdLists("AgentnewList").size(); j++){
                //Remove from the Agentnewlist and push node to rejectedList
                if (curr.getId() == IdLists.getIdLists("AgentnewList").get(j)[0]); {
                    curr.setStatus(Status.REJECT);
                    PizzaLists.getList("rejectedList").add(curr);
                    iterator.remove();
                    //Remove checked boxes from pane
                    int cbId = IdLists.getIdLists("AgentnewList").get(count)[1];
                    AgentNewOrdersVB.getChildren().remove(cbId);
                }
            }
            count++;
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
        int count = 0;
        for (Iterator<NodeData> iterator = PizzaLists.getList("finishedList").iterator(); iterator.hasNext();) {
            NodeData curr = iterator.next();
            for (int j = 0; j < IdLists.getIdLists("AgentfinishedList").size(); j++){
                if (curr.getId() == IdLists.getIdLists("AgentfinishedList").get(j)[0]) {
                    iterator.remove();
                    // Order completed, also search & remove node from allNodesList
                    for (Iterator<NodeData> allNodesIterator = PizzaLists.getList("allNodesList").iterator(); iterator.hasNext(); ) {
                        NodeData currAllNodes = allNodesIterator.next();
                        if (currAllNodes.getId() == IdLists.getIdLists("AgentfinishedList").get(j)[0]) {
                            allNodesIterator.remove();
                            int cbId = IdLists.getIdLists("AgentfinishedList").get(count)[1];
                            AgentFinishedOrdersVB.getChildren().remove(cbId);
                        }
                    }
                }
            }
            count++;
        }
        System.out.println("FINISHEDLIST: " + PizzaLists.getList("finishedList").toString());
    }

}
