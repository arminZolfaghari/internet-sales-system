import java.util.ArrayList;
import java.util.HashMap;

class Order {

    private int ID ;
    private Customer customer ;
    private int price ;
    private String status ;
    private HashMap<Good , Integer> items ;
    private ArrayList<Good> goods ;
    private ArrayList<Discount> discounts ;


    public Order(int ID, Customer c){
        this.ID = ID ;
        this.customer = c ;
        items = new HashMap<>() ;
        discounts = new ArrayList<>() ;
        status = "pending" ;
    }
//--------------------------------------------------------------------------//

    public int getID(){
        return ID ;
    }
    public String getStatus(){
        return status ;
    }
    public void setStatus (String status){
        this.status = status ;
    }

//---------------------------------------------------------------------------//

    public void addItem (Good good, int amount){
        if (items.containsKey(good)) {
            int oldValue ;
            oldValue = items.get(good) ;
            items.replace(good , oldValue + amount);
        }
        else
            items.put(good , amount ) ;
    }
    public void removeItem (Good good){
        items.remove(good) ;
    }
    public HashMap<Good, Integer> getItems(){
        return items ;
    }

//----------------------------------------------------------------------------//

    public int calculatePrice(){
        price = 0 ;
        for (Good good : items.keySet()) {
            price += (good.getPrice() * items.get(good));
        }
        for (Discount discount : discounts){
            price *= ((100.0 - discount.getPercentage()) / 100.0);
        }
        return price ;
    }

//------------------------------------------------------------------------------//

    public void addDiscount(Discount discount){
        discount.setOrder(this) ;
        discounts.add(discount) ;
    }

    //-----------------------------------------------------------------------------//
    public Customer getCustomer() {
        return customer;
    }
    public String toString() {
        return "Order{" +
                "id=" + ID +
                ", customer=" + customer +
                ", status='" + status + '\'' +
                ", items=" + items +
                ", discounts=" + discounts +
                '}';
    }

}