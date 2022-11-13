package application.Controllers;
import application.NodeData;
import application.PizzaLists;
import application.Status;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ResourceBundle;

public class OrderPizzaController extends SceneController implements Initializable {
    @FXML
    private ToggleGroup BaseType;
    @FXML
    private ToggleGroup Base;
    @FXML
    private CheckBox ChickenBox;
    @FXML
    private CheckBox JalapenoBox;
    @FXML
    private CheckBox OnionBox;

    private String[] pizzaOrderInfo = new String[3];
    private String[] toppings = new String[3];
    private NodeData temp = new NodeData();

    @FXML
    public void handleOrderInformation(ActionEvent actionEvent) throws IOException {
        temp.updateNode("", 0, Status.NEW, pizzaOrderInfo[0], toppings, pizzaOrderInfo[2]);
        PizzaLists.getList("tempList").add(temp);
        System.out.println(temp);
        switchToReviewOrder(actionEvent);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Base.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                RadioButton baseSelected = (RadioButton) Base.getSelectedToggle();

                if (baseSelected != null) {
                    pizzaOrderInfo[0] = baseSelected.getText();
                    System.out.println(Arrays.toString(Arrays.stream(pizzaOrderInfo).toArray()));
                }
            }
        });

        EventHandler<ActionEvent> toppingEvent = e -> {
            if (ChickenBox.isSelected()) {
                toppings[0] = ChickenBox.getText();
            }
            else {
                toppings[0] = "";
            }
            if (JalapenoBox.isSelected()) {
                toppings[1] = JalapenoBox.getText();
            }
            else {
                toppings[1] = "";
            }
            if (OnionBox.isSelected()) {
                toppings[2] = OnionBox.getText();
            }
            else {
                toppings[2] = "";
            }
            pizzaOrderInfo[1] = Arrays.toString(toppings);
            System.out.println(Arrays.toString(Arrays.stream(toppings).toArray()));
        };

        ChickenBox.setOnAction(toppingEvent);
        JalapenoBox.setOnAction(toppingEvent);
        OnionBox.setOnAction(toppingEvent);

        BaseType.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                RadioButton baseTypeSelected = (RadioButton) BaseType.getSelectedToggle();

                if (baseTypeSelected != null) {
                    pizzaOrderInfo[2] = baseTypeSelected.getText();
                    System.out.println(Arrays.toString(Arrays.stream(pizzaOrderInfo).toArray()));
                }
            }
        });

    }

}
