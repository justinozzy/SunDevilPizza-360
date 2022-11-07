package application;

public class Node {
    //Initialize variables, default empty
    private String name = "";
    private int id = -1;
    private Status status = null;
    private String base = "";
    private String toppings = "";
    private String bake = "";

    //Node Constructor, data shouldn't be modified -> no setters
    public Node(String name, int id, Status status, String base, String toppings, String bake){
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

    public String getToppings() {
        return toppings;
    }

}