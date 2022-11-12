package application.Controllers;
import application.NodeData;
import application.PizzaLists;
import application.Status;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class ChefScreenController extends SceneController {
    @FXML
    private CheckBox boxReady;
    @FXML
    private Label idReady;
    @FXML
    private CheckBox boxPickup;
    @FXML
    private Label idPickup;
    private ArrayList<Integer> idListReady = new ArrayList<Integer>();
    private ArrayList<Integer> idListPickup = new ArrayList<Integer>();

    public void addIDListReady(ActionEvent event) {
        //Call upon readyOrder checkboxes actions
        String[] idArr;
        int userId = 0;

        if (boxReady.isSelected()) {
            idArr = idReady.getText().split("\\s+");
            userId = Integer.parseInt(idArr[1]);
            idListReady.add(userId);
            System.out.println("IDList=" + idListReady.toString());
        } else {
            idListReady.remove(userId);
            System.out.println("IDList=" + idListReady.toString());
        }
    }

    public void addIDListPickup(ActionEvent event) {
        //Call upon pickupOrder checkboxes actions
        String[] idArr;
        int userId = 0;

        if (boxPickup.isSelected()) {
            idArr = idPickup.getText().split("\\s+");
            userId = Integer.parseInt(idArr[1]);
            idListPickup.add(userId);
            System.out.println("IDList=" + idListPickup.toString());
        } else {
            idListPickup.remove(userId);
            System.out.println("IDList=" + idListPickup.toString());
        }
    }

    public void readyToCookingList(){
        //Call upon readyOrder confirmCooking button
        for (Iterator<NodeData> iterator = PizzaLists.getList("readyList").iterator(); iterator.hasNext();) {
            NodeData curr = iterator.next();
            for (int j = 0; j < idListReady.size(); j++){
                if (curr.getId() == idListReady.get(j)); {
                    curr.setStatus(Status.COOKING);
                    PizzaLists.getList("cookingList").add(curr);
                    // set a cooking timer of 15s whenever a Node is added to Cooking List
                    startCookTimer(curr);       //Timer Method below
                    System.out.println("Timer set:ID= " + curr.getId());
                    iterator.remove();
                }
            }
        }
        System.out.println("COOKINGLIST: " + PizzaLists.getList("cookingList").toString() + " **Added to");
        System.out.println("READYLIST: " + PizzaLists.getList("readyList").toString() + " **Removed from");
    }

    public void pickupToFinishedList(){
        //Call upon pickupOrder confirmPickup button
        for (Iterator<NodeData> iterator = PizzaLists.getList("pickupList").iterator(); iterator.hasNext();) {
            NodeData curr = iterator.next();
            for (int j = 0; j < idListPickup.size(); j++){
                if (curr.getId() == idListPickup.get(j)); {
                    curr.setStatus(Status.FINISHED);
                    PizzaLists.getList("finishedList").add(curr);
                    iterator.remove();
                }
            }
            System.out.println(curr.getName());
        }
        System.out.println("PICKUPLIST: " + PizzaLists.getList("pickupList").toString() + " **Added to");
        System.out.println("FINISHEDLIST: " + PizzaLists.getList("finishedList").toString() + " **Removed from");
    }

    // Timer method to call to start a timer for the targetNode
    public void startCookTimer(NodeData targetNode){
        Timer timer = new Timer();
        TimerTask cookTask = new CookTimerTask(targetNode);
        //delay in miliseconds before task executes, 15s = 15,000ms
        long delay = 15000;
        // parameters = (task.run() to excute, time delay)
        timer.schedule(cookTask,delay);
    }

    //Helper class for TimerTask
    public class CookTimerTask extends TimerTask{
        NodeData targetNode;
        // constructor to pass in parameter targetNode
        public CookTimerTask(NodeData targetNode){
            this.targetNode = targetNode;
        }

        // run() called when timer.schedule waits "delay" ms
        @Override
        public void run() {
            for (Iterator<NodeData> iterator = PizzaLists.getList("cookingList").iterator(); iterator.hasNext();) {
                NodeData curr = iterator.next();
                // add targetNode to pickupList, remove from cooking List
                if (curr.getId() == targetNode.getId()); {
                    //If GUI needs method to update display, call here
                    curr.setStatus(Status.PICKUP);
                    PizzaLists.getList("pickupList").add(curr);
                    iterator.remove();
                }
                System.out.println("COOKING DONE: " + curr.getId());
                System.out.println("PICKUPLIST: " + PizzaLists.getList("pickupList").toString() + " **Added to");
                System.out.println("COOKINGLIST: " + PizzaLists.getList("cookingList").toString() + " **Removed from");

            }
        }
    }

}

