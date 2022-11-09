package application.Controllers;
import application.Node;
import application.PizzaLists;
import application.Status;
import javafx.event.ActionEvent;

import javax.swing.*;
import java.util.ArrayList;

public class OrderPizzaController extends SceneController {
    String name = "";
    int id;
    String base = "";

    // [0] = chicken, [1] = jalapenos, [2] = onions
    String[] toppings = {"","",""};
    String bake = "";

    /* Setters methods to call for each individual Radio buttons, automatically re-set upon selecting a different base,
       remove function not needed. */
    public void setBaseCheese(){
        base = "Cheese";
    }

    public void setBaseVeggie(){
        base = "Veggie";
    }

    public void setBaseMeatLovers(){
        base = "Meat Lover's";
    }

    public void setBakeHandTossed(){
        bake = "Hand Tossed";
    }

    public void setBakeThinCrust(){
        bake = "Thin Crust";
    }

    public void setBakePan(){
        bake = "Pan";
    }

    //CheckBoxes, different functions for Selected and Unselected Action events.
    public void addToppingsChicken(){
        toppings[0] = "Chicken";
    }

    public void removeToppingsChicken(){
        toppings[0] = "";
    }

    public void addToppingsJalapenos(){
        toppings[1] = "Jalapenos";
    }

    public void removeToppingsJalapenos(){
        toppings[1] = "";
    }

    public void addToppingsOnions(){
        toppings[2] = "Onions";
    }

    public void removeToppingsOnions(){
        toppings[2] = "";
    }


    public double calculateTotal(){
        //Front End WIP, total should be displayed on GUI

        //Order Confirmed in OrderPizzaPane, only calculate total and don't create Node until ReviewedOrderPane.
        //Calculate total based on current selections.
        double total = 0;

        switch(base){
            case "Cheese":
                total += 10.0;
                break;
            case "Veggie":
                total += 12.0;
                break;
            case "Meat Lover's":
                total += 15.0;
                break;
            default:
                //base = ""; Shouldn't be reached, since a base must be selected.
                System.out.println("[Error] Unexpected BASE in OrderPizzaController.calculateTotal()");
                break;
        }

        switch(bake){
            case "Hand Tossed":
                total += 1.0;
                break;
            case "Thin Crust":
                total += 1.5;
                break;
            case "Pan":
                total += 2.0;
                break;
            default:
                //bake = ""; Shouldn't be reached, since a bake must be selected.
                System.out.println("[Error] Unexpected BAKE in OrderPizzaController.calculateTotal()");
                break;
        }

        for(int i=0; i<3; i++){
            if(toppings[i].equals("")){
                // topping at [i] not selected, do nothing
            }
            else if(i == 0){
                //add chicken price to total
                total += 2;
            }
            else if(i == 1){
                //add jalapeno price to total
                total += 1.5;
            }
            else if(i == 2){
                //add onion price to total
                total += 1.5;
            }

        }

        return total;
    }

    public void createNewOrder(ActionEvent event){
        //Order Confirmed in ReviewOrderPane, create new Node and insert into Linked List
        // status always = NEW at this point
        Status status = Status.NEW;

        Node newOrder = new Node(name, id, status, base, toppings, bake);

        // WIP, might not need tempList? Maybe just add straight to newList?
        PizzaLists.getList("tempList").add(newOrder);
    }

}
