package application.Controllers;

import application.PizzaDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class StudentLoginController extends SceneController {
    @FXML
    private TextField StudentLoginPassword;
    private String[] verify = new String[3];

    public void studentVerification(ActionEvent event) throws IOException {
        try {
            verify = PizzaDatabase.getStudent(Integer.parseInt(StudentLoginPassword.getText()));
            if (verify[0] == null) {
                System.out.println("Error");
            } else {
                CheckOrdersController.setStudentId(Integer.parseInt(verify[0]));
                switchToCheckOrders(event);
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid format.");
        }
    }
}
