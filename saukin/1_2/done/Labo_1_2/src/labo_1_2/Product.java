package labo_1_2;

/**
 *
 * @author saukin
 */
public class Product {
    
    private int prodNo;
    private double price;
    private int nVents;

    
    public Product(int prodNo, double price) {
        this.prodNo = prodNo;
        this.price = price;
    }

    public int getnVents() {
        return nVents;
    }

    public void raiseVents(int nVents) {
        this.nVents += nVents;
    }
    
    public int getProdNo() {
        return prodNo;
    }

    public void setProdNo(int prodNo) {
        this.prodNo = prodNo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
}
