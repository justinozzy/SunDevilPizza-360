package application.Controllers;

import application.NodeData;
import application.PizzaDatabase;
import application.PizzaLists;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Objects;

public class ReviewOrderController extends SceneController {
    @FXML
    private Button ConfirmButton;
    @FXML
    private TextField ReviewName;
    @FXML
    private TextField ReviewID;

    private NodeData temp = PizzaLists.getList("tempList").get(0);

    public void createNewOrder(NodeData order) {
        if (PizzaDatabase.getStudent(order.getId())[1] != null) {
            PizzaLists.getList("newList").add(order);
            // also added to allNodes List
            PizzaLists.getList("allNodesList").add(order);
            PizzaLists.getList("tempList").remove(0);
            System.out.println(("Order added to NewList"));
        }
        else {
            throw new NumberFormatException();
        }
    }

    //Controller for the Confirm Order button
    @FXML
    public void handleConfirmation(ActionEvent event) throws Throwable {
        //Create a new order and switch to the main menu
        try {
            temp.setName(ReviewName.getText());
            temp.setId(Integer.parseInt(ReviewID.getText()));
            createNewOrder(temp);
            ConfirmButton.setDisable(true);

            //Print each order in the newList
            for (Iterator<NodeData> iterator = PizzaLists.getList("newList").iterator(); iterator.hasNext(); ) {
                NodeData curr = iterator.next();
                System.out.println(curr.toString());
            }
            switchToMainMenu(event);
            System.out.println(PizzaLists.getList("allNodesList").toString());
        }
        catch (NumberFormatException e) {
            System.out.println("Error");
        }
    }
}
