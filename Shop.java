import java.util.ArrayList;
import java.util.HashMap;

class Shop {

    private String name;
    private int income ;
    private ArrayList<Customer> customers ;
    private ArrayList<Repository> repositories ;
    private ArrayList<Repository> repositoriesByID ;
    private ArrayList<Good> goods ;
    private ArrayList<Discount> discounts ;
    private ArrayList<Order> orders ;
    private HashMap<Good, Integer> itemSold ;

    public Shop(String name) {
        this.name = name ;
        customers = new ArrayList<>() ;
        repositories = new ArrayList<>() ;
        repositoriesByID = new ArrayList<>() ;
        goods = new ArrayList<>() ;
        discounts = new ArrayList<>() ;
        itemSold = new HashMap<>() ;
        orders = new ArrayList<>() ;
        income = 0;
    }
    //---------------------------------------------------------------------//
    public void addCustomer(Customer c) {
        customers.add(c);
    }
    public Customer serachCustomer(int ID) {
        for (Customer customer : customers) {
            if (customer.getID() == ID)
                return customer;
        }
        return null;
    }
    public Customer[] getCustomers() {
        Customer[] customers = new Customer[this.customers.size()];
        for (int i = 0; i < this.customers.size(); i++) {
            customers[i] = this.customers.get(i);
        }
        return customers;
    }

//-------------------------------------------------------------------------//

    public void addRepository(Repository r) {
        for (int i = 0; i < repositories.size(); i++) {
            if (r.getCapacity() < repositories.get(i).getCapacity())
                repositories.add(i, r);
            return ;
        }
        repositories.add(r);
    }

    public void addRepositoryByID(Repository r) {
        for (int i = 0; i < repositoriesByID.size(); i++) {
            if (r.getID() < repositoriesByID.get(i).getID()) {
                repositoriesByID.add(i, r);
                return;
            }
        }
        repositoriesByID.add(r);
    }

    public Repository[] getRepositories() {
        Repository[] repositories = new Repository[this.repositories.size()];
        for (int i = 0; i < this.repositories.size(); i++) {
            repositories[i] = this.repositories.get(i);
        }
        return repositories;
    }

//---------------------------------------------------------------------------------//

    public int getIncom() {
        return this.income;
    }

    public void setIncome(int income) {
        this.income = income;
    }
//-----------------------------------------------------------------------------------//

    public void addGood(Good g) {
        goods.add(g);
    }
    public Good[] getGoods() {
        Good[] goods = new Good[this.goods.size()];
        for (int i = 0; i < this.goods.size(); i++) {
            goods[i] = this.goods.get(i);
        }
        return goods;
    }

    public Good serachGood(int id) {
        for (Good good : goods) {
            if (good.getID() == id)
                return good;
        }
        return null;
    }

    public void increamentGood(Good g, int amount) {
        for (Repository repository : repositories) {
            if (repository.getFreeCapacity() >= amount)
                repository.addGood(g, amount);
            break ;
        }
    }
    public void decreamentGood(Good good, int amount) {
        for (Repository repository : repositoriesByID) {
            if (repository.removeGood(good, amount))
                break ;
        }
    }

//--------------------------------------------------------------------------//

    public void addDiscount(Discount d, Order o) {
        if (discounts.indexOf(d) != -1) {
            o.addDiscount(d);
            discounts.remove(d);
        }
    }
    public void addDiscount(Discount discount){
        discounts.add(discount) ;
    }
    public Discount searchDiscount(int ID) {
        for (Discount discount : discounts) {
            if (discount.getID() == ID)
                return discount;
        }
        return null;
    }

//---------------------------------------------------------------------------//

    public void addOrder(Order order) {
        orders.add(order);
    }
    public Order[] getOrders() {
        Order[] out = new Order[orders.size()];
        for (int i = 0; i < orders.size(); i++) {
            out[i] = orders.get(i);
        }
        return out;
    }

    public Order searchOrder(int id) {
        for (Order order : orders) {
            if (order.getID() == id)
                return order;
        }
        return null;
    }

//-----------------------------------------------------------------------------//

    public HashMap<Good, Integer> getItemsSold(){
        return itemSold ;
    }
    public void addSoldItems(Order order) {
        HashMap<Good, Integer> orderGoods = order.getItems();
        for (Good good : orderGoods.keySet()) {
            int newGoodCount = orderGoods.get(good);
            if (itemSold.containsKey(good)) {
                newGoodCount += itemSold.get(good);
            }
            itemSold.put(good, newGoodCount);

            decreamentGood(good, orderGoods.get(good));
        }
    }
}