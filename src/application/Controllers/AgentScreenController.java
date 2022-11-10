package application.Controllers;
import application.PizzaLists;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import java.util.ArrayList;
import java.util.Iterator;
import application.NodeData;

public class AgentScreenController extends SceneController {
    @FXML
    private CheckBox temp;
    @FXML
    private Label id;
    private ArrayList<Integer> idList = new ArrayList<Integer>();

    public void addIDList(ActionEvent event){
        String[] idArr;
        int userId = 0;

        if(temp.isSelected()){
            idArr = id.getText().split("\\s+");
            userId = Integer.parseInt(idArr[1]);
            idList.add(userId);
            System.out.println("IDList=" + idList.toString());
            System.out.println("ID = " + userId);
        }
        else{
            idList.remove(userId);
            System.out.println("IDList=" + idList.toString());
        }

    }

    public void newToReadyList(){
        for (Iterator<NodeData> iterator = PizzaLists.getList("newList").iterator(); iterator.hasNext();) {
            NodeData curr = iterator.next();
            for (int j = 0; j < idList.size(); j++){
                if (curr.getId() == idList.get(j)); {
                    PizzaLists.getList("readyList").add(curr);
                    iterator.remove();
                }
            }
            System.out.println(curr.getName());
        }
        System.out.println("READYLIST: " + PizzaLists.getList("readyList").toString());
        System.out.println("NEWLIST: " + PizzaLists.getList("newList").toString());
    }

}
