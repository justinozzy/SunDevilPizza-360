package application.Controllers;

import application.NodeData;
import application.PizzaDatabase;
import application.PizzaLists;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Objects;
import java.util.ResourceBundle;

public class ReviewOrderController extends SceneController implements Initializable {
    @FXML
    private Button ConfirmButton;
    @FXML
    private TextField ReviewName;
    @FXML
    private TextField ReviewID;
    @FXML
    private VBox ReviewOrderVBox;
    @FXML
    private VBox CostVBox;

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
            PizzaDatabase.changeStudentBalance(temp.getId(), temp.getTotalCost());

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

    public void changeOrder(ActionEvent event) throws IOException {
        PizzaLists.getList("tempList").remove(0);
        switchToOrderPizza(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Get all the nodes and iterate through them
        Iterator<NodeData> iterator = PizzaLists.getList("tempList").iterator();
        NodeData curr = iterator.next();

        Label orderLabels;
        Label costLabels;
        StringBuilder toppings = new StringBuilder();

        //Build toppings string with selected toppings
        for (int i = 0; i < 3; i++) {
            if (curr.getToppings()[i] != null) {
                toppings.append(String.format(" %s", curr.getToppings()[i]));
            }
        }

        //Determine if the ID of the student matches this node and print it to the string if it does
        orderLabels = new Label(String.format("\t%s %s pizza \n\t with\n\t%s\n", curr.getBake(), curr.getBase(),
                toppings));
        orderLabels.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        costLabels = new Label("  " + Integer.toString(curr.getTotalCost()));
        costLabels.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        costLabels.setFont(new Font("Andalus", 40));
        ReviewOrderVBox.getChildren().add(orderLabels);
        CostVBox.getChildren().add(costLabels);
    }
}
