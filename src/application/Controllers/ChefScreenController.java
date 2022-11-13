package application.Controllers;
import application.IdLists;
import application.NodeData;
import application.PizzaLists;
import application.Status;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.*;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;

public class ChefScreenController extends SceneController {
    @FXML
    private Button ChefGetOrdersButton;
    private int delay;

    public void ChefGetInfo(){
        ChefGetOrdersButton.setDisable(true);
        //Ready Orders
        createCheckBox(PizzaLists.getList("readyList"), ChefReadyToCookVB);

        //Cooking Orders
        createCookingText();

        //Pickup Orders
        createCheckBox(PizzaLists.getList("pickupList"), ChefReadyForPickupVB);
    }

    public void ChefGetInfoUpdate(){
        ChefReadyToCookVB.getChildren().removeAll();
        ChefReadyForPickupVB.getChildren().removeAll();
        ChefCookingVB.getChildren().removeAll(ChefCookingVB.getChildren());
        ChefGetInfo();
        System.out.println("***ChefPane Update Called***");
    }

    public void createCookingText(){
        //Get all the nodes and iterate through them
        for (Iterator<NodeData> iterator = PizzaLists.getList("cookingList").iterator(); iterator.hasNext();) {

            Label orderLabels = new Label();
            NodeData curr = iterator.next();
            StringBuilder toppings = new StringBuilder();

            //Build toppings string with selected toppings
            for (int i = 0; i < 3; i++) {
                if (curr.getToppings()[i] != null) {
                    toppings.append(String.format(" %s", curr.getToppings()[i]));
                }
            }
            String info = "Name: " + curr.getName() + "\n" + "Id: " + curr.getId() + "\n" +  "Order: " + curr.getBase() + " " +  curr.getBake() + toppings;
            orderLabels.setText(info);
            ChefCookingVB.getChildren().add(orderLabels);
        }
    }

    public void cookingTimerDisplay() {
        readyToCookingList();
        startCookTimer();
        System.out.println("CookingList: "+ PizzaLists.getList("cookingList").toString() );
    }

    public void readyToCookingList(){
        //Call upon readyOrder confirmCooking button
        for (Iterator<NodeData> iterator = PizzaLists.getList("readyList").iterator(); iterator.hasNext();) {
            NodeData curr = iterator.next();
            for (int j = 0; j < IdLists.getIdLists("AgentreadyList").size(); j++){
                if (curr.getId() == IdLists.getIdLists("AgentreadyList").get(j)[0]); {
                    curr.setStatus(Status.COOKING);
                    PizzaLists.getList("cookingList").add(curr);
                    System.out.println("Timer set:ID= " + curr.getId());
                    //Remove check boxes from ReadyToCook Box
                    int[] cbId = new int[1];
                    cbId[0] = (IdLists.getIdLists("AgentreadyList").get(j)[1]);
                    IdLists.getIdLists("AgentreadyList").remove(j);

                    for (int i = 0; i < ChefReadyToCookVB.getChildren().size(); i++) {
                        if (Integer.toString(cbId[0]).equals(ChefReadyToCookVB.getChildren().get(i).getId())) {
                            ChefReadyToCookVB.getChildren().remove(i);
                        }
                    }
                    iterator.remove();
                    System.out.println(curr);
                    break;
                }
            }

        }
        ChefGetInfoUpdate();
        System.out.println("COOKINGLIST: " + PizzaLists.getList("cookingList").toString() + " **Added to");
        System.out.println("READYLIST: " + PizzaLists.getList("readyList").toString() + " **Removed from");
    }

    public void pickupToFinishedList(){
        //Call upon pickupOrder confirmPickup button
        for (Iterator<NodeData> iterator = PizzaLists.getList("pickupList").iterator(); iterator.hasNext();) {
            NodeData curr = iterator.next();
            for (int j = 0; j < IdLists.getIdLists("AgentpickupList").size(); j++){
                if (curr.getId() == IdLists.getIdLists("AgentpickupList").get(j)[0]); {
                    curr.setStatus(Status.FINISHED);
                    PizzaLists.getList("finishedList").add(curr);
                    //Remove checked boxes from ReadyToPickup box
                    int[] cbId = new int[1];
                    cbId[0] = (IdLists.getIdLists("AgentpickupList").get(j)[1]);
                    IdLists.getIdLists("AgentpickupList").remove(j);

                    for (int i = 0; i < ChefReadyForPickupVB.getChildren().size(); i++) {
                        if (Integer.toString(cbId[0]).equals(ChefReadyForPickupVB.getChildren().get(i).getId())) {
                            ChefReadyForPickupVB.getChildren().remove(i);
                        }
                    }
                    iterator.remove();
                    System.out.println(curr);
                    break;
                }
            }
        }
        ChefGetInfoUpdate();
        System.out.println("PICKUPLIST: " + PizzaLists.getList("pickupList").toString() + " **Added to");
        System.out.println("FINISHEDLIST: " + PizzaLists.getList("finishedList").toString() + " **Removed from");
    }

    // Timer method to call to start a timer for the targetNode
    public void startCookTimer(){
        Timer timer = new Timer();
        //TimerTask cookTask = new CookTimerTask(targetNode);
        //delay in miliseconds before task executes, 15s = 15,000ms
        long delay = 5000;
        // parameters = (task.run() to excute, time delay)
        timer.schedule(new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        for (Iterator<NodeData> iterator = PizzaLists.getList("cookingList").iterator(); iterator.hasNext(); ) {
                            NodeData curr = iterator.next();
                            // add targetNode to pickupList, remove from cooking List
                            // If GUI needs method to update display, call here
                            curr.setStatus(Status.PICKUP);
                            PizzaLists.getList("pickupList").add(curr);
                            iterator.remove();

                            System.out.println("COOKING DONE: " + curr.getId());
                            System.out.println("PICKUPLIST: " + PizzaLists.getList("pickupList").toString() + " **Added to");
                            System.out.println("COOKINGLIST: " + PizzaLists.getList("cookingList").toString() + " **Removed from");
                        }
                        ChefGetInfoUpdate();
                    }
                });
            }
        }, delay);
    }
}
