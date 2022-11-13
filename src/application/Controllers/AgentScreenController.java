package application.Controllers;
import application.PizzaLists;
import application.Status;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import application.NodeData;
import javafx.scene.control.ScrollPane;

public class AgentScreenController extends SceneController {
    @FXML
    private CheckBox boxNew;
    @FXML
    private Label idNew;
    @FXML
    private CheckBox boxFinished;
    @FXML
    private Label idFinished;

    @FXML
    private ScrollPane AgentNewOrdersScroll;

    private ArrayList<Integer> idListNew = new ArrayList<Integer>();
    private ArrayList<Integer> idListFinished = new ArrayList<Integer>();


    public void addIDListNew(ActionEvent event){
        //Call upon newOrder checkboxes actions
        String[] idArr;
        int userId = 0;

        if(boxNew.isSelected()){
            idArr = idNew.getText().split("\\s+");
            userId = Integer.parseInt(idArr[1]);
            idListNew.add(userId);
        }
        else{
            idListNew.remove(userId);
        }

    }

    public void createCheckBoxes(ActionEvent event){

    }
    public void addIDListFinished(ActionEvent event){
        //call upon finishedOrder checkboxes actions
        String[] idArr;
        int userId = 0;

        if(boxFinished.isSelected()){
            idArr = idFinished.getText().split("\\s+");
            userId = Integer.parseInt(idArr[1]);
            idListFinished.add(userId);
        }
        else{
            idListFinished.remove(userId);
        }

    }

    public void newToReadyList(){
        //Call upon newOrder confirm button
        for (Iterator<NodeData> iterator = PizzaLists.getList("newList").iterator(); iterator.hasNext();) {
            NodeData curr = iterator.next();
            for (int j = 0; j < idListNew.size(); j++){
                if (curr.getId() == idListNew.get(j)); {
                    curr.setStatus(Status.READY);
                    PizzaLists.getList("readyList").add(curr);
                    iterator.remove();
                }
            }
            System.out.println(curr.toString());
        }
        System.out.println("READYLIST: " + PizzaLists.getList("readyList").toString() + "**Added to");
        System.out.println("NEWLIST: " + PizzaLists.getList("newList").toString() + " **Removed from");
    }

    public void newToRejectedList(){
        //Call upon newOrder reject button
        for (Iterator<NodeData> iterator = PizzaLists.getList("newList").iterator(); iterator.hasNext();) {
            NodeData curr = iterator.next();
            for (int j = 0; j < idListNew.size(); j++){
                if (curr.getId() == idListNew.get(j)); {
                    curr.setStatus(Status.REJECT);
                    PizzaLists.getList("rejectedList").add(curr);
                    iterator.remove();
                }
            }
            System.out.println(curr.toString());
        }
        System.out.println("REJECTEDLIST: " + PizzaLists.getList("rejectedList").toString() + "**Added to");
        System.out.println("NEWLIST: " + PizzaLists.getList("newList").toString() + "**Removed from");
    }

    public void finishedListPickedUp(){
        //Call upon finishedOrder [Confirmed Picked-Up] button
        //When a finished order is selected and Picked-Up, order completed, remove it from finished list.
        for (Iterator<NodeData> iterator = PizzaLists.getList("finishedList").iterator(); iterator.hasNext();) {
            NodeData curr = iterator.next();
            for (int j = 0; j < idListFinished.size(); j++){
                if (curr.getId() == idListFinished.get(j)) {
                    iterator.remove();
                    // Order completed, also search & remove node from allNodesList
                    for (Iterator<NodeData> allNodesIterator = PizzaLists.getList("allNodesList").iterator(); iterator.hasNext(); ) {
                        NodeData currAllNodes = allNodesIterator.next();
                        if (currAllNodes.getId() == idListFinished.get(j)) {
                            allNodesIterator.remove();
                        }
                    }
                }

            }
            System.out.println(curr.toString());
        }
        System.out.println("FINISHEDLIST: " + PizzaLists.getList("finishedList").toString());
    }

}
