package application.Controllers;

import application.NodeData;
import application.PizzaLists;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ResourceBundle;

public class CheckOrdersController extends SceneController implements Initializable {
    private static Integer newId;

    @FXML
    private VBox OrdersVBox;
    @FXML
    private VBox StatusVBox;

    public static void setStudentId(Integer id) {
        newId = id;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Iterator<NodeData> iterator = PizzaLists.getList("allNodesList").iterator(); iterator.hasNext();) {
            Label orderLabels;
            Label statusLabels;
            NodeData curr = iterator.next();
            StringBuilder toppings = new StringBuilder();

            for (int i = 0; i < 3; i++) {
                if (curr.getToppings()[i] != null) {
                    toppings.append(String.format(" %s", curr.getToppings()[i]));
                }
            }

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
}
