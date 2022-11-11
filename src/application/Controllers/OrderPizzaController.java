package application.Controllers;
import application.NodeData;
import application.PizzaLists;
import application.Status;
import com.sun.javafx.menu.MenuItemBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.Iterator;

public class OrderPizzaController extends SceneController {
    @FXML
    private Button ConfirmButton;

    public static void createNewOrder(NodeData order){
        //Order Confirmed in ReviewOrderPane, create new Node and insert into Linked List
        // status always = NEW at this point
        String[] toppings = new String[3];
        toppings[0] = "Chicken";
        order.updateNode("Justin Jin", 1,Status.NEW,"Cheese",toppings,"Thin Crust");


        PizzaLists.getList("newList").add(order);
        System.out.println(("Order added to NewList"));
    }

    //Controller for the Confirm Order button
    @FXML
    public void handleConfirmation(ActionEvent event) throws IOException {
        //Create a new order and switch to the main menu
        switchToMainMenu(event);
        OrderPizzaController.createNewOrder(temp);
        ConfirmButton.setDisable(true);

        //Print each order in the newList
        for (Iterator<NodeData> iterator = PizzaLists.getList("newList").iterator(); iterator.hasNext();) {
            NodeData curr = iterator.next();
            System.out.println("NewList: " + curr.getName());
        }
    }
}
