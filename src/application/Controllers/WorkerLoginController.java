package application.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class WorkerLoginController extends SceneController {
    public void checkAgentMenu(ActionEvent event) throws IOException {

        if(switchToWorkerScreen(event) == 1)
        {
            switchToWorkerScreen(event);
            createCheckBox("get agent list");
        }
        else if(switchToWorkerScreen(event) == 2)
        {
            switchToWorkerScreen(event);
            createCheckBox("get chef list");
        }
        else{
            System.out.println("Invalid worker credentials");
        }



    }

}
