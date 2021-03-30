package labo_1_2;

/**
 *
 * @author saukin
 */
public class Vin {
    
    public static int nVins = 0;
    public static double totalPrice = 0;
    
    private String name;
    private int type;
    private String origin;
    private double prix;

    public Vin(String name, int type, String origin, double prix) {
        if (argsGood(type, prix)) {
            this.name = name;
            this.type = type;
            this.origin = origin;
            this.prix = prix;
            nVins++;
            totalPrice += prix;
        } 
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        if (prix > 0) {
            totalPrice -= this.prix;
            this.prix = prix;
            totalPrice += this.prix;
        }
    }

    @Override
    public String toString() {
        String typeS = getTypeS();
        return String.format("%s est un vin %s de %s et son prix est de %.2f $\n",
                this.name, typeS, this.origin, this.prix);
    }

    private String getTypeS() {
        String typeS = "";
        switch (type) {
            case 1:
                typeS = "rouge";
                break;
            case 2:
                typeS = "blanc";
                break;
            case 3:
                typeS = "rosé";
                break;
        }
        return typeS;
    }

    private boolean argsGood(int t, double p) {
        return t > 0 && t < 4 && p > 0;
    }

}
