
//Majd Salti ID#1218690330 TH Janaka Balasooriya 9am
package application;
 
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

//Change Student Log In to add Check Orders
//Change Main Menu to look like Student Menu (check figma)
//Only Change Label on Student Orders
//Threading and Updates on Javafx
import static application.PizzaDatabase.*;

public class Main extends Application {
 
 @Override
 public void start(Stage stage) {
  try {
   
   Parent root = FXMLLoader.load(getClass().getResource("Panes/MainMenu.fxml"));
   Scene scene = new Scene(root);
   String css = this.getClass().getResource("Panes/application.css").toExternalForm();
   scene.getStylesheets().add(css);
   stage.setScene(scene);
   stage.show();
   
  } catch(Exception e) {
   e.printStackTrace();
  }
 } 

 public static void main(String[] args) {
  //Create the databases whenever the program is launched; Can comment after first run/databases exist
  createDatabases();
  launch(args);
 }
}