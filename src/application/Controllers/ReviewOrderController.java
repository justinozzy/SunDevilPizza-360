package application.Controllers;

import application.NodeData;
import application.PizzaLists;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Iterator;

public class ReviewOrderController extends SceneController {
    @FXML
    private Button ConfirmButton;
    @FXML
    private TextField ReviewName;
    @FXML
    private TextField ReviewID;

    private NodeData temp = PizzaLists.getList("tempList").get(0);

    public void createNewOrder(NodeData order){
        PizzaLists.getList("newList").add(order);
        // also added to allNodes List
        PizzaLists.getList("allNodesList").add(order);
        PizzaLists.getList("tempList").remove(0);
        System.out.println(("Order added to NewList"));
    }

    //Controller for the Confirm Order button
    @FXML
    public void handleConfirmation(ActionEvent event) throws IOException {
        //Create a new order and switch to the main menu
        switchToMainMenu(event);
        temp.setName(ReviewName.getText());
        temp.setId(Integer.parseInt(ReviewID.getText()));
        createNewOrder(temp);
        ConfirmButton.setDisable(true);

        //Print each order in the newList
        for (Iterator<NodeData> iterator = PizzaLists.getList("newList").iterator(); iterator.hasNext();) {
            NodeData curr = iterator.next();
            System.out.println(curr.toString());
        }
        System.out.println(PizzaLists.getList("allNodesList").toString());
    }
}
