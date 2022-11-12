package application.Controllers;
import application.NodeData;
import application.PizzaLists;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class ChefScreenController extends SceneController {
    @FXML
    private CheckBox boxReady;
    @FXML
    private Label idReady;
    @FXML
    private CheckBox boxPickup;
    @FXML
    private Label idPickup;
    private ArrayList<Integer> idListReady = new ArrayList<Integer>();
    private ArrayList<Integer> idListPickup = new ArrayList<Integer>();

    public void addIDListReady(ActionEvent event) {
        //Call upon readyOrder checkboxes actions
        String[] idArr;
        int userId = 0;

        if (boxReady.isSelected()) {
            idArr = idReady.getText().split("\\s+");
            userId = Integer.parseInt(idArr[1]);
            idListReady.add(userId);
            System.out.println("IDList=" + idListReady.toString());
            System.out.println("ID = " + userId);
        } else {
            idListReady.remove(userId);
            System.out.println("IDList=" + idListReady.toString());
        }
    }

    public void addIDListPickup(ActionEvent event) {
        //Call upon readyOrder checkboxes actions
        String[] idArr;
        int userId = 0;

        if (boxPickup.isSelected()) {
            idArr = idPickup.getText().split("\\s+");
            userId = Integer.parseInt(idArr[1]);
            idListPickup.add(userId);
            System.out.println("IDList=" + idListPickup.toString());
            System.out.println("ID = " + userId);
        } else {
            idListPickup.remove(userId);
            System.out.println("IDList=" + idListPickup.toString());
        }
    }

    public void readyToCookingList(){
        //Call upon readyOrder confirmCooking button
        for (Iterator<NodeData> iterator = PizzaLists.getList("readyList").iterator(); iterator.hasNext();) {
            NodeData curr = iterator.next();
            for (int j = 0; j < idListReady.size(); j++){
                if (curr.getId() == idListReady.get(j)); {
                    PizzaLists.getList("cookingList").add(curr);
                    iterator.remove();
                }
            }
            System.out.println(curr.getName());
        }
        System.out.println("COOKINGLIST: " + PizzaLists.getList("cookingList").toString());
        System.out.println("READYLIST: " + PizzaLists.getList("readyList").toString());
    }

    public void pickupToFinishedList(){
        //Call upon readyOrder confirmCooking button
        for (Iterator<NodeData> iterator = PizzaLists.getList("pickupList").iterator(); iterator.hasNext();) {
            NodeData curr = iterator.next();
            for (int j = 0; j < idListPickup.size(); j++){
                if (curr.getId() == idListPickup.get(j)); {
                    PizzaLists.getList("finishedList").add(curr);
                    iterator.remove();
                }
            }
            System.out.println(curr.getName());
        }
        System.out.println("PICKUPLIST: " + PizzaLists.getList("pickupList").toString());
        System.out.println("FINISHEDLIST: " + PizzaLists.getList("finishedList").toString());
    }

    public void cookingDone(){
        Timer cook = new Timer();
        TimerTask cookTask = new CookingTimerTask();
        long delay = 15;
        cook.schedule(cookTask,delay);
    }

}
