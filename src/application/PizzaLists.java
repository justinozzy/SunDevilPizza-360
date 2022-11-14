package application;
import java.util.LinkedList;

public class PizzaLists {
    //Initialize linked lists, default empty
    //The linked list Nodes are different from the Node class, the LinkedList Nodes contains data = Node Class;
    static private LinkedList<NodeData> tempList = new LinkedList<NodeData>();       //Temp order
    static private LinkedList<NodeData> newList = new LinkedList<NodeData>();       //New orders
    static private LinkedList<NodeData> readyList = new LinkedList<NodeData>();     //ready to cook
    static private LinkedList<NodeData> cookingList = new LinkedList<NodeData>();   //cooking
    static private LinkedList<NodeData> pickupList = new LinkedList<NodeData>();    //ready for pick-up
    static private LinkedList<NodeData> finishedList = new LinkedList<NodeData>();  //finished orders
    static private LinkedList<NodeData> rejectedList = new LinkedList<NodeData>();  //rejected orders
    static private LinkedList<NodeData> allNodesList = new LinkedList<NodeData>();

    static public String getName(LinkedList<NodeData> list){
        if (tempList.equals(list)) {
            return "tempList";
        } else if (newList.equals(list)) {
            return "newList";
        } else if (readyList.equals(list)) {
            return "readyList";
        } else if (cookingList.equals(list)) {
            return "cookingList";
        } else if (pickupList.equals(list)) {
            return "pickupList";
        } else if (finishedList.equals(list)) {
            return "finishedList";
        } else if (rejectedList.equals(list)) {
            return "rejectedList";
        } else if (allNodesList.equals(list)) {
            return "allNodesList";
        }
        System.out.println("[Error] Unexpected string in PizzaList.getList(string), allNodesList returned as default");
        return "allNodesList";
    }

    // list getter, parameter should be string of name of the list to get.
    static public LinkedList<NodeData> getList(String listName){
        switch(listName){
            case "tempList":
                return tempList;
            case "newList":
                return newList;
            case "readyList":
                return readyList;
            case "cookingList":
                return cookingList;
            case "pickupList":
                return pickupList;
            case "finishedList":
                return finishedList;
            case "rejectedList":
                return rejectedList;
            case "allNodesList":
                return allNodesList;
            default:
                System.out.println("[Error] Unexpected string in PizzaList.getList(string), allNodesList returned as default");
                return allNodesList;
        }

    }
}
