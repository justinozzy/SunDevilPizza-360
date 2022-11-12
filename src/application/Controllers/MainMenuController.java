package application.Controllers;

import application.NodeData;
import application.PizzaLists;
import application.Status;

import java.util.Iterator;
import java.util.LinkedList;

public class MainMenuController extends SceneController {
    // All in progress orders of the current logged-in student, print from this list when displaying CheckOrders
    private LinkedList<NodeData> currStudentOrders;

    //Call when CheckOrders button on MainMenuPane pressed, call this method before printing from currStudentOrders
    public void updateStudentOrders() {
        LinkedList<NodeData> temp = new LinkedList<NodeData>();

        for (Iterator<NodeData> iterator = PizzaLists.getList("allNodesList").iterator(); iterator.hasNext();) {
            NodeData curr = iterator.next();
            // currStudentID should be safe from not initiated (-1), as CheckOrders button only accessible when logged in
            //
            if (curr.getId() == SceneController.currStudentID) {
                temp.add(curr);
            }
        }
        currStudentOrders = temp;
    }

    // template FrontEnd method for displaying the Orders of the logged in student
    public void displayCheckOrders(){
        //print currStudentOrders
    }
}
