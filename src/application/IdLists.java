package application;

import java.util.ArrayList;
import java.util.LinkedList;

public class IdLists {
    static private ArrayList<int[]> AgentnewList = new ArrayList<>();
    static private ArrayList<int[]> AgentreadyList = new ArrayList<>();    //ready to cook
    static private ArrayList<int[]> AgentcookingList = new ArrayList<>();   //cooking
    static private ArrayList<int[]> AgentpickupList = new ArrayList<>();  //ready for pick-up
    static private ArrayList<int[]> AgentfinishedList = new ArrayList<>();  //finished orders
    static private ArrayList<int[]> AgentrejectedList = new ArrayList<>();  //rejected orders
    static private ArrayList<int[]> AgentallNodesList = new ArrayList<>();

    static public String getName(LinkedList<int[]>)

    static public ArrayList<int[]> getIdLists(String listName){
            switch(listName){
                case "AgentnewList":
                    return AgentnewList;
                case "AgentreadyList":
                    return AgentreadyList;
                case "AgentcookingList":
                    return AgentcookingList;
                case "AgentpickupList":
                    return AgentpickupList;
                case "AgentfinishedList":
                    return AgentfinishedList;
                case "AgentrejectedList":
                    return AgentrejectedList;
                case "AgentallNodesList":
                    return AgentallNodesList;
                default:
                    return AgentallNodesList;
            }
    }
}
