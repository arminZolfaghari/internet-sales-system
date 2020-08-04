class Discount {

    private int ID ;
    private int percent ;
    private Order order ;


    public Discount(int ID, int percent){
        this.ID = ID ;
        this.percent = percent ;
    }
    public int getID() {
        return ID;
    }
    public void setOrder (Order order){
        this.order = order ;
    }
    public Order getOrder(){
        return order ;
    }
    public int getPercentage(){
        return percent ;
    }

}