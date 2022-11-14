package application;

import java.util.Arrays;

public class NodeData {
    //Initialize variables, default empty
    private String name = "";
    private int id = -1;
    private Status status = null;
    private String base = "";

    //NOTE: Index for each toppings are static!!
    // [0] = chicken, [1] = jalapenos, [2] = Onions
    private String[] toppings = {"","",""};
    private String bake = "";
    private int totalCost = 0;

    //Node Constructor, data shouldn't be modified -> no setters

    public NodeData(){
    }

    public void updateNode(String name, int id, Status status, String base, String[] toppings, String bake){
        this.name = name;
        this.id = id;
        this.status = status;
        this.base = base;
        this.toppings = toppings;
        this.bake = bake;
    }

    //Node Getters
    public int getId() {
        return id;
    }
    
    public void setStatus(Status status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public String getBase() {
        return base;
    }

    public String getBake() {
        return bake;
    }

    public String getName() {
        return name;
    }

    public String[] getToppings() {
        return toppings;
    }

    public void setTotalCost(Integer total) {
        this.totalCost = total;
    }

    public int getTotalCost() {
        return totalCost;
    }

    //LinkList toString returns id of Node
    @Override
    public String toString(){
        return String.format("Name: %s\n ID: %d\n Status: %s\n Base: %s\n Bake: %s\n Toppings: %s\n",
                this.name, this.id, this.status, this.base, this.bake, Arrays.toString(this.toppings));
    }

}
