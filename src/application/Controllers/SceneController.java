package application.Controllers;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import application.PizzaDatabase;
import application.PizzaLists;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;
import application.NodeData;

public class SceneController {
	 NodeData temp = new NodeData();
	 private Stage stage;
	 private Scene scene;
	 private Parent root;
	 
	 @FXML
	 private TextField WorkerID;
	 @FXML
	 private TextField WorkerPassword;
	 @FXML
	 private Button WorkerLogInButton;
	 @FXML
	 private Label ValidWorkerID;
	 @FXML
	 private Label ValidWorkerPassword;

	 @FXML
	 public VBox AgentNewOrdersVB;

	@FXML
	 public VBox AgentFinishedOrdersVB;
	@FXML
	public VBox ChefReadyToCookVB;

	@FXML
	public VBox ChefCookingVB;

	@FXML
	public VBox ChefReadyForPickupVB;



	//when student logs in, store their ID, default -1 (Not logged in), used by CheckOrders function in MainMenuController
	 public static int currStudentID = -1;
//if there is a waya we can wait for a controller or handler ot finish runnign and hten do some action, then we can skip the initialization
	public void createCheckBox(LinkedList<NodeData> list, VBox VB)  {
		VB.setSpacing(5);

		for(Iterator<NodeData> iterator = list.iterator(); iterator.hasNext();){
			NodeData curr = iterator.next();

			String topping = Arrays.toString(curr.getToppings());
			topping = topping.replaceAll("[\\[\\]]","");
			topping = topping.replaceAll("[,]", " ");
			String info = "Name: " + curr.getName() + "\n" + "Id: " + curr.getId() + "\n" +  "Order: " + curr.getBase() + " " +  curr.getBake() + " " +  topping;

			CheckBox cb = new CheckBox(info);
			VB.getChildren().add(cb);

		}

	}

	 public void switchToMainMenu(ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getResource("../Panes/MainMenu.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 String css = this.getClass().getResource("../Panes/application.css").toExternalForm();
		 scene.getStylesheets().add(css);
		 stage.setScene(scene);
		 stage.show();
	 }
	 
	 public void switchToStudentLogInScreen(ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getResource("../Panes/StudentLogInScreen.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 String css = this.getClass().getResource("../Panes/application.css").toExternalForm();
		 scene.getStylesheets().add(css);
		 stage.setScene(scene);
		 stage.show();
	 }
	 
	 public void switchToWorkerLogInScreen(ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getResource("../Panes/WorkerLogInScreen.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 String css = this.getClass().getResource("../Panes/application.css").toExternalForm();
		 scene.getStylesheets().add(css);
		 stage.setScene(scene);
		 stage.show();
	 }
	 public void switchToStudentMenu(ActionEvent event) throws IOException {
		 // !!! student login needs implementation, below is what i guess would be added based on WorkerLogin
		 /* if (PizzaDatabase.getStudent(StudentID.getText(), StudentPassword.getText()) == 1);
		 		{
		 		currStudentID = StudentID.getText();  // add this line
		 		switch to studentMenuPane
		 		}
		  */
		 root = FXMLLoader.load(getClass().getResource("../Panes/StudentMenu.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 String css = this.getClass().getResource("../Panes/application.css").toExternalForm();
		 scene.getStylesheets().add(css);
		 stage.setScene(scene);
		 stage.show();
	 }
	 public void switchToOrderPizza(ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getResource("../Panes/OrderPizza.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 String css = this.getClass().getResource("../Panes/application.css").toExternalForm();
		 scene.getStylesheets().add(css);
		 stage.setScene(scene);
		 stage.show();
	 }
	 public void switchToReviewOrder(ActionEvent event) throws IOException {

		 root = FXMLLoader.load(getClass().getResource("../Panes/ReviewOrder.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 String css = this.getClass().getResource("../Panes/application.css").toExternalForm();
		 scene.getStylesheets().add(css);
		 stage.setScene(scene);
		 stage.show();
	 }
	 public void switchToCheckOrders(ActionEvent event) throws IOException {
		 root = FXMLLoader.load(getClass().getResource("../Panes/CheckOrders.fxml"));
		 stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		 scene = new Scene(root);
		 String css = this.getClass().getResource("../Panes/application.css").toExternalForm();
		 scene.getStylesheets().add(css);
		 stage.setScene(scene);
		 stage.show();
	 }

	 public int switchToWorkerScreen(ActionEvent event) throws IOException {
		 //Verify the login information
		 try {
			 //Check if a worker is an Agent
			 if (PizzaDatabase.getEmployee(WorkerID.getText(), WorkerPassword.getText()) == 1) {
				 root = FXMLLoader.load(getClass().getResource("../Panes/AgentScreen.fxml"));
				 stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				 //Our VBox doesnt exist and we want to load it. But how?
				 scene = new Scene(root);
				 String css = this.getClass().getResource("../Panes/application.css").toExternalForm();
				 scene.getStylesheets().add(css);
				 stage.setScene(scene);
				 stage.show();
				 return 1;
			 }
			 //Check if a worker is a chef
			 else if (PizzaDatabase.getEmployee(WorkerID.getText(), WorkerPassword.getText()) == 2) {
				 root = FXMLLoader.load(getClass().getResource("../Panes/ChefScreen.fxml"));
				 stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				 scene = new Scene(root);
				 String css = this.getClass().getResource("../Panes/application.css").toExternalForm();
				 scene.getStylesheets().add(css);
				 stage.setScene(scene);
				 stage.show();
				 return 2;
			 }
			 //Credentials are invalid
			 else {
				 ValidWorkerID.setText("Invalid ID");
				 return 0;
			 }
		 }
		 //Empty text fields
		 catch (NullPointerException e) {
			 System.err.println(e.getMessage());
		 }
		 return 0;
	 }
}
