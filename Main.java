import java.util.*;
public class Main {

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in) ;
        Shop digiKala = new Shop("DigiKala") ;
        int x = 1 ;
        while (x == 1){
            String s1 = scan.next() ;
            switch (s1) {
                case "add" : {
                    String s2 = scan.next() ;
                    switch (s2){
                        case "customer" : {
                            int customerID = scan.nextInt() ;
                            String customerName = scan.next() ;
                            Customer customer = new Customer(customerName , customerID) ;
                            digiKala.addCustomer(customer) ;
                            break ;
                        }
                        case "good" : {
                            int goodID = scan.nextInt() ;
                            String goodName = scan.next() ;
                            int goodPrice = scan.nextInt() ;
                            int goodAmount = scan.nextInt() ;
                            Good good = digiKala.serachGood(goodID) ;
                            if (good == null)
                            {
                                good = new Good(goodName , goodID , goodPrice);
                                digiKala.addGood(good) ;
                            }
                            digiKala.increamentGood(good , goodAmount);
                            break ;
                        }
                        case "repository" : {
                            int repositoryID = scan.nextInt() ;
                            int repositoryCapacity = scan.nextInt() ;
                            Repository repository = new Repository(repositoryID , repositoryCapacity) ;
                            digiKala.addRepository(repository) ;
                            digiKala.addRepositoryByID(repository);
                            break ;
                        }
                        case "order" : {
                            int orderID = scan.nextInt() ;
                            int orderCustomerID = scan.nextInt() ;
                            Customer customer = digiKala.serachCustomer(orderCustomerID) ;
                            Order order = new Order(orderID , customer) ;
                            customer.addOrder(order) ;
                            digiKala.addOrder(order) ;
                            break ;
                        }
                        case "balance" : {
                            int customerID = scan.nextInt() ;
                            int addAmount = scan.nextInt() ;
                            Customer customer = digiKala.serachCustomer(customerID) ;
                            int oldAmount = customer.getBalance() ;
                            int newAmount = oldAmount + addAmount ;
                            customer.setBalance(newAmount) ;
                            break ;
                        }
                        case "item" : {
                            int orderID = scan.nextInt() ;
                            int goodID = scan.nextInt() ;
                            int goodAmount = scan.nextInt() ;
                            Order order = digiKala.searchOrder(orderID) ;
                            if (order == null) {
                                break;
                            }
                            Good good = digiKala.serachGood(goodID) ;
                            order.addItem(good , goodAmount);
                            break ;
                        }
                        case "discount" : {
                            int discountID = scan.nextInt() ;
                            int discountPercent = scan.nextInt() ;
                            Discount discount = new Discount(discountID , discountPercent) ;
                            digiKala.addDiscount(discount);
                            break ;
                        }
                    }
                    break ;
                }
                case "report" : {
                    String s3 = scan.next() ;
                    switch (s3) {
                        case "customers" : {
                            Customer[] cutomers = digiKala.getCustomers() ;
                            for (Customer customer : cutomers ) {
                                System.out.println(customer);
                            }
                            break ;
                        }
                        case "repositories" : {
                            Repository[] repositories = digiKala.getRepositories() ;
                            for (Repository repository : repositories ) {
                                System.out.println(repository);
                            }
                            break ;
                        }
                        case "income" : {
                            System.out.println(digiKala.getIncom()) ;
                            break ;
                        }
                    }
                    break ;
                }
                case "remove" : {
                    String s4 = scan.next() ;
                    switch (s4){
                        case "item" : {
                            int orderID = scan.nextInt() ;
                            int goodID = scan.nextInt() ;
                            Good good = digiKala.serachGood(goodID) ;
                            Order order = digiKala.searchOrder(orderID) ;
                            order.removeItem(good);
                            break ;
                        }
                    }
                    break ;
                }
                case "submit" : {
                    String s5 = scan.next() ;
                    switch (s5){
                        case "order": {
                            int orderID = scan.nextInt();
                            Order order = digiKala.searchOrder(orderID);
                            Customer customer = order.getCustomer();

                            int price = customer.submitOrder(order);
                            if (price != -1) {
                                digiKala.addSoldItems(order);
                                digiKala.setIncome(digiKala.getIncom() + price);
                            }
                            break;
                        }
                        case "discount" : {
                            int orderID = scan.nextInt() ;
                            int discountID = scan.nextInt() ;
                            Order order = digiKala.searchOrder(orderID);
                            Discount discount = digiKala.searchDiscount(discountID);
                            digiKala.addDiscount(discount , order);
                            break ;
                        }
                    }
                    break ;
                }
                case "terminate" : {
                    x = 2 ;
                    break ;
                }
            }
        }
    }
}


