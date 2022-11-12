package application.Controllers;
import application.PizzaLists;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import application.NodeData;

public class AgentScreenController extends SceneController {
    @FXML
    private CheckBox boxNew;
    @FXML
    private Label idNew;
    @FXML
    private CheckBox boxFinished;
    @FXML
    private Label idFinished;

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
            System.out.println("IDList=" + idListNew.toString());
            System.out.println("ID = " + userId);
        }
        else{
            idListNew.remove(userId);
            System.out.println("IDList=" + idListNew.toString());
        }

    }

    public void addIDListFinished(ActionEvent event){
        //call upon finishedOrder checkboxes actions
        String[] idArr;
        int userId = 0;

        if(boxNew.isSelected()){
            idArr = idNew.getText().split("\\s+");
            userId = Integer.parseInt(idArr[1]);
            idListFinished.add(userId);
            System.out.println("IDList=" + idListFinished.toString());
            System.out.println("ID = " + userId);
        }
        else{
            idListFinished.remove(userId);
            System.out.println("IDList=" + idListFinished.toString());
        }

    }

    public void newToReadyList(){
        //Call upon newOrder confirm button
        for (Iterator<NodeData> iterator = PizzaLists.getList("newList").iterator(); iterator.hasNext();) {
            NodeData curr = iterator.next();
            for (int j = 0; j < idListNew.size(); j++){
                if (curr.getId() == idListNew.get(j)); {
                    PizzaLists.getList("readyList").add(curr);
                    iterator.remove();
                }
            }
            System.out.println(curr.toString());
        }
        System.out.println("READYLIST: " + PizzaLists.getList("readyList").toString());
        System.out.println("NEWLIST: " + PizzaLists.getList("newList").toString());
    }

    public void newToRejectedList(){
        //Call upon newOrder reject button
        for (Iterator<NodeData> iterator = PizzaLists.getList("newList").iterator(); iterator.hasNext();) {
            NodeData curr = iterator.next();
            for (int j = 0; j < idListNew.size(); j++){
                if (curr.getId() == idListNew.get(j)); {
                    PizzaLists.getList("rejectedList").add(curr);
                    iterator.remove();
                }
            }
            System.out.println(curr.toString());
        }
        System.out.println("REJECTEDLIST: " + PizzaLists.getList("rejectedList").toString());
        System.out.println("NEWLIST: " + PizzaLists.getList("newList").toString());
    }

    public void finishedListPickedUp(){
        //Call upon finishedOrder [Confirmed Picked-Up] button
        //When a finished order is selected and Picked-Up, order completed, remove it from finished list.
        for (Iterator<NodeData> iterator = PizzaLists.getList("finishedList").iterator(); iterator.hasNext();) {
            NodeData curr = iterator.next();
            for (int j = 0; j < idListFinished.size(); j++){
                if (curr.getId() == idListFinished.get(j)); {
                    iterator.remove();
                }
            }
            System.out.println(curr.toString());
        }
        System.out.println("FINISHEDLIST: " + PizzaLists.getList("finishedList").toString());
    }

}
