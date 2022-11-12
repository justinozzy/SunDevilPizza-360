package application.Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class SceneController {

	 private Stage stage;
	 private Scene scene;
	 private Parent root;
	 
	 @FXML
	 private TextField WorkerID;
	 @FXML
	 private Button WorkerLogInButton;
	 @FXML
	 private Label ValidWorkerID;
	 @FXML
	 private Label ValidWorkerPassword;
	 
	 public void switchToMainMenu(ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getResource("../Panes/MainMenu.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 String css = this.getClass().getResource("Panes/application.css").toExternalForm();
		 scene.getStylesheets().add(css);
		 stage.setScene(scene);
		 stage.show();
	 }
	 
	 public void switchToStudentLogInScreen(ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getResource("../Panes/StudentLogInScreen.fxml"));
		 System.out.println("root="+root);
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 String css = this.getClass().getResource("Panes/application.css").toExternalForm();
		 scene.getStylesheets().add(css);
		 stage.setScene(scene);
		 stage.show();
	 }
	 
	 public void switchToWorkerLogInScreen(ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getResource("../Panes/WorkerLogInScreen.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 String css = this.getClass().getResource("Panes/application.css").toExternalForm();
		 scene.getStylesheets().add(css);
		 stage.setScene(scene);
		 stage.show();
	 }
	 public void switchToStudentMenu(ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getResource("../Panes/StudentMenu.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 String css = this.getClass().getResource("Panes/application.css").toExternalForm();
		 scene.getStylesheets().add(css);
		 stage.setScene(scene);
		 stage.show();
	 }
	 public void switchToOrderPizza(ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getResource("../Panes/OrderPizza.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 String css = this.getClass().getResource("Panes/application.css").toExternalForm();
		 scene.getStylesheets().add(css);
		 stage.setScene(scene);
		 stage.show();
	 }
	 public void switchToReviewOrder(ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getResource("../Panes/ReviewOrder.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 String css = this.getClass().getResource("Panes/application.css").toExternalForm();
		 scene.getStylesheets().add(css);
		 stage.setScene(scene);
		 stage.show();
	 }
	 public void switchToCheckOrders(ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getResource("../Panes/CheckOrders.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 String css = this.getClass().getResource("Panes/application.css").toExternalForm();
		 scene.getStylesheets().add(css);
		 stage.setScene(scene);
		 stage.show();
	 }
	 public void switchToWorkerScreen(ActionEvent event) throws IOException {
		 if (WorkerID.getText().toString().equals("agent")) {
			 root = FXMLLoader.load(getClass().getResource("../Panes/AgentScreen.fxml"));
			 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			 scene = new Scene(root);
			 String css = this.getClass().getResource("Panes/application.css").toExternalForm();
			 scene.getStylesheets().add(css);
			 stage.setScene(scene);
			 stage.show();
		 } else if (WorkerID.getText().toString().equals("chef")) {
			 root = FXMLLoader.load(getClass().getResource("../Panes/ChefScreen.fxml"));
			 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			 scene = new Scene(root);
			 String css = this.getClass().getResource("Panes/application.css").toExternalForm();
			 scene.getStylesheets().add(css);
			 stage.setScene(scene);
			 stage.show();
		 } else {ValidWorkerID.setText("Invalid ID");}
		 
	 }
}
