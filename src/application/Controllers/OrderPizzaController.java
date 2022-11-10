package application.Controllers;
import application.NodeData;
import application.PizzaLists;
import application.Status;

public class OrderPizzaController extends SceneController {
    /*
    public int calculateTotal(){
        //Front End WIP, total should be displayed on GUI

        //Order Confirmed in OrderPizzaPane, only calculate total and don't create Node until ReviewedOrderPane.
        //Calculate total based on current selections.
        int total = 0;

        switch(base){
            case "Cheese":
                total += 10;
                break;
            case "Veggie":
                total += 12;
                break;
            case "Meat Lover's":
                total += 15;
                break;
            default:
                //base = ""; Shouldn't be reached, since a base must be selected.
                System.out.println("[Error] Unexpected BASE in OrderPizzaController.calculateTotal()");
                break;
        }

        switch(bake){
            case "Hand Tossed":
                total += 0;
                break;
            case "Thin Crust":
                total += 1;
                break;
            case "Pan":
                total += 2;
                break;
            default:
                //bake = ""; Shouldn't be reached, since a bake must be selected.
                System.out.println("[Error] Unexpected BAKE in OrderPizzaController.calculateTotal()");
                break;
        }

        for(int i=0; i<3; i++){
            if(toppings[i].equals("")){
                // topping at [i] not selected, do nothing
            }
            else if(i == 0){
                //add chicken price to total
                total += 2;
            }
            else if(i == 1){
                //add jalapeno price to total
                total += 1;
            }
            else if(i == 2){
                //add onion price to total
                total += 1;
            }

        }

        return total;
    }
    */
    public static void createNewOrder(NodeData order){

        //Order Confirmed in ReviewOrderPane, create new Node and insert into Linked List
        // status always = NEW at this point
        String[] toppings = new String[3];
        Status status = Status.NEW;
        toppings[0] = "Chickent";
        order.updateNode("Justin Jin", 1,Status.NEW,"Cheese",toppings,"Thin Crust");


        PizzaLists.getList("newList").add(order);
        System.out.println(("Order added to NewList"));

        /*WIP, SwitchToMainMenu should be called either here, or when the button is clicked.
          but i'm not sure if the fxml can call 2 action handlers.

         */
    }

}
