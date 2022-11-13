package application.Controllers;

import application.PizzaDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Objects;


public class StudentLoginController extends SceneController {
    @FXML
    TextField studentId;
    String[] student = new String[3];

    @FXML
    public void handleStudentLogin(ActionEvent event) throws IOException {
        try {
            student = PizzaDatabase.getStudent(Integer.parseInt(studentId.getText()));
            if (Objects.equals(student[0], "0")) {
                System.out.println("Invalid id");
            }
            else {
                switchToCheckOrders(event);
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Incorrect TextField format (Empty or non-integer)");
        }
    }
}
