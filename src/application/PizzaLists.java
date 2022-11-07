package application;
import java.util.LinkedList;

public class PizzaLists {
    //Initialize linked lists, default empty
    //The linked list Nodes are different from the Node class, the LinkedList Nodes contains data = Node Class;
    private LinkedList<Node> newList = new LinkedList<Node>();
    private LinkedList<Node> readyList = new LinkedList<Node>();
    private LinkedList<Node> cookingList = new LinkedList<Node>();
    private LinkedList<Node> pendingList = new LinkedList<Node>();
    private LinkedList<Node> finishedList = new LinkedList<Node>();
    private LinkedList<Node> rejectedList = new LinkedList<Node>();
    private LinkedList<Node> tempList = new LinkedList<Node>();

    // list getter, parameter should be string of name of the list to get.
    public LinkedList<Node> getList(String listName){
        switch(listName){
            case "newList":
                return newList;
            case "readyList":
                return readyList;
            case "cookingList":
                return cookingList;
            case "pendingList":
                return pendingList;
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
