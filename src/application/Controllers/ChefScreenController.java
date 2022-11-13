package application.Controllers;
import application.IdLists;
import application.NodeData;
import application.PizzaLists;
import application.Status;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.util.*;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;

public class ChefScreenController extends SceneController {
    @FXML
    private Button ChefGetOrdersButton;
    private static Integer newId;


    private LinkedList<NodeData> ChefReady = new LinkedList<NodeData>();
    private LinkedList<NodeData> ChefCooking = new LinkedList<NodeData>();
    private LinkedList<NodeData> ChefPickup = new LinkedList<NodeData>();

    public void ChefGetInfo(){
        ChefGetOrdersButton.setDisable(true);
        //Ready Orders
        createCheckBox(PizzaLists.getList("readyList"), ChefReadyToCookVB);

        //Cooking Orders
        createCheckBox(PizzaLists.getList("cookingList"), ChefCookingVB);

        //Pickup Orders
        createCheckBox(PizzaLists.getList("pickupList"), ChefReadyForPickupVB);
    }

    public void ChefGetInfoUpdate(){
        ChefReadyToCookVB.getChildren().removeAll();
        ChefReadyForPickupVB.getChildren().removeAll();
        ChefGetInfo();
    }

    public static void setStudentId(Integer id) {
        newId = id;
    }

    public void createCookingText(){
        //Get all the nodes and iterate through them
        for (Iterator<NodeData> iterator = PizzaLists.getList("cookingList").iterator(); iterator.hasNext();) {
            Label orderLabels;
            Label statusLabels;
            NodeData curr = iterator.next();
            StringBuilder toppings = new StringBuilder();

            //Build toppings string with selected toppings
            for (int i = 0; i < 3; i++) {
                if (curr.getToppings()[i] != null) {
                    toppings.append(String.format(" %s", curr.getToppings()[i]));
                }
            }

            //Determine if the ID of the student matches this node and print it to the string if it does
            if (curr.getId() == newId) {
                orderLabels = new Label(String.format("%s %s pizza with%s\n", curr.getBake(), curr.getBase(),
                        toppings));
                orderLabels.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
                statusLabels = new Label(curr.getStatus().toString());
                OrdersVBox.getChildren().add(orderLabels);
                StatusVBox.getChildren().add(statusLabels);
            }
        }
    }

    public void readyToCookingList(){
        //Call upon readyOrder confirmCooking button
        int count = 0;
        for (Iterator<NodeData> iterator = PizzaLists.getList("readyList").iterator(); iterator.hasNext();) {
            NodeData curr = iterator.next();
            for (int j = 0; j < IdLists.getIdLists("AgentreadyList").size(); j++){
                if (curr.getId() == IdLists.getIdLists("AgentreadyList").get(j)[0]); {
                    curr.setStatus(Status.COOKING);
                    PizzaLists.getList("cookingList").add(curr);
                    // set a cooking timer of 15s whenever a Node is added to Cooking List
                    startCookTimer(curr);       //Timer Method below
                    System.out.println("Timer set:ID= " + curr.getId());
                    iterator.remove();
                    //Remove check boxes from ReadyToCook Box
                    int cbId = IdLists.getIdLists("AgentreadyList").get(count)[1];
                    ChefReadyToCookVB.getChildren().remove(cbId);
                }
            }
            count++;
        }
        ChefGetInfo();
        System.out.println("COOKINGLIST: " + PizzaLists.getList("cookingList").toString() + " **Added to");
        System.out.println("READYLIST: " + PizzaLists.getList("readyList").toString() + " **Removed from");
    }

    public void pickupToFinishedList(){
        //Call upon pickupOrder confirmPickup button
        int count = 0;
        for (Iterator<NodeData> iterator = PizzaLists.getList("pickupList").iterator(); iterator.hasNext();) {
            NodeData curr = iterator.next();
            for (int j = 0; j < IdLists.getIdLists("AgentpickupList").size(); j++){
                if (curr.getId() == IdLists.getIdLists("AgentpickupList").get(j)[0]); {
                    curr.setStatus(Status.FINISHED);
                    PizzaLists.getList("finishedList").add(curr);
                    iterator.remove();
                    //Remove checked boxes from ReadyToPickup box
                    int cbId = IdLists.getIdLists("AgentpickupList").get(count)[1];
                    ChefReadyForPickupVB.getChildren().remove(cbId);
                }
            }
            count++;
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
                    ChefGetInfoUpdate();
                }
                System.out.println("COOKING DONE: " + curr.getId());
                System.out.println("PICKUPLIST: " + PizzaLists.getList("pickupList").toString() + " **Added to");
                System.out.println("COOKINGLIST: " + PizzaLists.getList("cookingList").toString() + " **Removed from");

            }
        }
    }

}

/*
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
 */