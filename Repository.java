import java.util.HashMap;

class Repository {

    private int ID ;
    private int capacity ;
    private int freeCapacity ;
    private HashMap<Good , Integer> goods ;
    private int oldValue ;


    public Repository(int ID, int capacity){
        this.ID = ID ;
        this.capacity = capacity ;
        freeCapacity = capacity ;
        goods = new HashMap<>() ;
    }

    //--------------------------------------------------------------------------//
    public int getID(){
        return ID ;
    }
    public int getCapacity(){
        return capacity ;
    }
    public int getFreeCapacity(){
        return  freeCapacity ;
    }

//----------------------------------------------------------------------------//

    public HashMap<Good,Integer> getGoods(){
        return  goods ;
    }
    public void addGood(Good g, int amount) {
        if (goods.containsKey(g)) {
            oldValue = goods.get(g);
            goods.replace(g, oldValue + amount);
        } else {
            goods.put(g, amount) ;
        }
        freeCapacity = freeCapacity - amount ;
    }
    public boolean removeGood(Good g, int amount) {
        if (goods.containsKey(g) && goods.get(g) >= amount) {
            oldValue = goods.get(g) ;
            goods.replace(g, oldValue - amount) ;
            freeCapacity = freeCapacity + amount ;
            return true ;
        }
        return false ;
    }

//-----------------------------------------------------------------------------//

    public boolean checkRepository(Good good, int amount) {
        return goods.containsKey(good) && goods.get(good) >= amount ;
    }

//-------------------------------------------------------------------------------//

    public String toString() {
        return ID + "," + capacity + "," + freeCapacity ;
    }
}
