import java.util.ArrayList;

class Customer {

    private String name ;
    private int ID ;
    private int balance ;
    private ArrayList<Order> totalOrders ;
    private ArrayList<Order> pendingOrders ;
    private ArrayList<Order> submittedOrders ;

    public Customer(String name, int ID){
        this.name = name ;
        this.ID = ID ;
        totalOrders = new ArrayList<>() ;
        pendingOrders = new ArrayList<>() ;
        submittedOrders = new ArrayList<>() ;
    }

    //---------------------------------------------------------------------//
    public String getName(){
        return name ;
    }
    public int getID(){
        return ID ;
    }
    public int getBalance(){
        return balance ;
    }
    public void setBalance(int amount){
        this.balance = amount ;
    }

//----------------------------------------------------------------------//

    public void addOrder(Order order){
        pendingOrders.add(order) ;
        totalOrders.add(order) ;
    }
    public Order[] getTotalOrders() {
        Order[] orders = new Order[this.totalOrders.size()];
        for (int i = 0; i < this.totalOrders.size(); i++) {
            orders[i] = this.totalOrders.get(i);
        }
        return orders;
    }

    public Order[] getPendingOrders() {
        Order[] pendingOrders = new Order[this.pendingOrders.size()];
        for (int i = 0; i < this.pendingOrders.size(); i++) {
            pendingOrders[i] = this.pendingOrders.get(i);
        }
        return pendingOrders;
    }

    public Order[] getSubmittedOrders() {
        Order[] submittedOrders = new Order[this.submittedOrders.size()];
        for (int i = 0; i < this.submittedOrders.size(); i++) {
            submittedOrders[i] = this.submittedOrders.get(i);
        }
        return submittedOrders;
    }

    public int submitOrder(Order order) {
        int price = order.calculatePrice();
        if (price > balance)
            return -1;
        else {
            balance -= price;
            pendingOrders.remove(order);
            order.setStatus("submitted");
            submittedOrders.add(order);
            return price;
        }
    }

//----------------------------------------------------------------------------------------//

    public String toString() {
        return ID + "," + name + "," + balance + "," + totalOrders.size() + "," + submittedOrders.size() ;
    }
}