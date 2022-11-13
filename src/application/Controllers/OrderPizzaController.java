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
import javafx.scene.control.*;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
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
    @FXML
    private Button CalculateTotalButton;
    @FXML
    private TextArea TotalTextArea;

    private String[] pizzaOrderInfo = new String[3];
    private String[] toppings = new String[3];
    private NodeData temp = new NodeData();
    private int toppingTotal = 0;
    private int pizzaTypeTotal = 0;
    private int pizzaBaseTotal = 0;

    @FXML
    public void handleOrderInformation(ActionEvent actionEvent) throws IOException {
        temp.updateNode("", 0, Status.NEW, pizzaOrderInfo[0], toppings, pizzaOrderInfo[2]);
        temp.setTotalCost((toppingTotal + pizzaTypeTotal + pizzaBaseTotal));
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

                    switch (baseSelected.getText()) {
                        case "Cheese":
                            pizzaBaseTotal = 2;
                            break;
                        case "Veggie":
                            pizzaBaseTotal = 4;
                            break;
                        case "Meat Lover's":
                            pizzaBaseTotal = 5;
                            break;
                        default:
                            pizzaBaseTotal = 0;
                            break;
                    }
                }
            }
        });

        EventHandler<ActionEvent> chickenEvent = e -> {
            if (ChickenBox.isSelected()) {
                toppings[0] = ChickenBox.getText();
                toppingTotal += 1;
            }
            else {
                toppings[0] = "";
                toppingTotal -= 1;
            }
            pizzaOrderInfo[1] = Arrays.toString(toppings);
            System.out.println(Arrays.toString(Arrays.stream(toppings).toArray()));
        };

        EventHandler<ActionEvent> jalapenoEvent = e -> {
            if (JalapenoBox.isSelected()) {
                toppings[1] = JalapenoBox.getText();
                toppingTotal += 1;
            }
            else {
                toppings[1] = "";
                toppingTotal -= 1;
            }
            pizzaOrderInfo[1] = Arrays.toString(toppings);
            System.out.println(Arrays.toString(Arrays.stream(toppings).toArray()));
        };

        EventHandler<ActionEvent> onionEvent = e -> {
            if (OnionBox.isSelected()) {
                toppings[2] = OnionBox.getText();
                toppingTotal += 1;
            }
            else {
                toppings[2] = "";
                toppingTotal -= 1;
            }
            pizzaOrderInfo[1] = Arrays.toString(toppings);
            System.out.println(Arrays.toString(Arrays.stream(toppings).toArray()));
        };

        ChickenBox.setOnAction(chickenEvent);
        JalapenoBox.setOnAction(jalapenoEvent);
        OnionBox.setOnAction(onionEvent);

        BaseType.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                RadioButton baseTypeSelected = (RadioButton) BaseType.getSelectedToggle();

                if (baseTypeSelected != null) {
                    pizzaOrderInfo[2] = baseTypeSelected.getText();
                    System.out.println(Arrays.toString(Arrays.stream(pizzaOrderInfo).toArray()));

                    switch (baseTypeSelected.getText()) {
                        case "Hand Tossed":
                            pizzaTypeTotal = 10;
                            break;
                        case "Thin Crust":
                            pizzaTypeTotal = 8;
                            break;
                        case "Pan":
                            pizzaTypeTotal = 12;
                            break;
                        default:
                            pizzaTypeTotal = 0;
                            break;
                    }
                }
            }
        });

        EventHandler<ActionEvent> displayTotalEvent = e -> {
            TotalTextArea.setText(Integer.toString(toppingTotal + pizzaTypeTotal + pizzaBaseTotal));
        };

        CalculateTotalButton.setOnAction(displayTotalEvent);
    }

}
