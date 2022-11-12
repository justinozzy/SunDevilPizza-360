package application;
import java.util.LinkedList;

public class PizzaLists {
    //Initialize linked lists, default empty
    //The linked list Nodes are different from the Node class, the LinkedList Nodes contains data = Node Class;
    static private LinkedList<NodeData> newList = new LinkedList<NodeData>();       //New orders
    static private LinkedList<NodeData> readyList = new LinkedList<NodeData>();     //ready to cook
    static private LinkedList<NodeData> cookingList = new LinkedList<NodeData>();   //cooking
    static private LinkedList<NodeData> pickupList = new LinkedList<NodeData>();    //ready for pick-up
    static private LinkedList<NodeData> finishedList = new LinkedList<NodeData>();  //finished orders
    static private LinkedList<NodeData> rejectedList = new LinkedList<NodeData>();  //rejected orders
    static private LinkedList<NodeData> tempList = new LinkedList<NodeData>();

    // list getter, parameter should be string of name of the list to get.
    static public LinkedList<NodeData> getList(String listName){
        switch(listName){
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
            case "tempList":
                return tempList;
            default:
                System.out.println("[Error] Unexpected string in PizzaList.getList(string), tempList returned as default");
                return tempList;
        }

    }
}
