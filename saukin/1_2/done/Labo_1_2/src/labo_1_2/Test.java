package labo_1_2;

import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author saukin
 */
public class Test {
    
    
    public static void main(String[] args) throws IOException {

        Test test = new Test();
        test.perform();
        
    }
    
    private void testVin() {
        String messageHeader = "Voici les %d vins\n"
                + "Le prix total des vins est de %.2f $\n";

        Vin[] vins = new Vin[4];
        vins[0] = new Vin("Vini Fantini", 1, "Italie", 9.8);
        vins[1] = new Vin("Vert Portuguse", 2, "Portugalie", 10.8);
        vins[2] = new Vin("Rose Belarusien", 3, "Belarus", 11.1);

        JTextArea textArea = new JTextArea(String.format(messageHeader,
                Vin.nVins, Vin.totalPrice));
        for (Vin vin : vins) {
            if (vin != null) {
                textArea.append("\t");
                textArea.append(vin.toString());
            }
        }

        vins[0].setPrix(vins[0].getPrix() + 2);
        vins[1].setPrix(9);
        vins[1].setOrigin("Portugal");
        vins[2].setName("Italien blanc");
        vins[2].setType(2);
        vins[2].setOrigin(vins[0].getOrigin());

        vins[3] = new Vin("Rose Belarusien", 3, "Belarus", 11.1);

        textArea.append("\n");
        textArea.append(String.format(messageHeader, Vin.nVins, Vin.totalPrice));

        for (Vin vin : vins) {
            textArea.append("\t");
            textArea.append(vin.toString());
        }

        JOptionPane.showMessageDialog(null, textArea, null, JOptionPane.PLAIN_MESSAGE, null);
    }

    private void testVentes() throws IOException {

        ProdSales ps = new ProdSales();
        ps.doSales();

    }
    
    
    private void perform() throws IOException {
        String[] options = {"TestVin", "TestVentes", "Quitter"};

        int choix;
        do {
            choix = JOptionPane.showOptionDialog(null,
                    "Faites votre choix :", "MENU DES TESTS",
                    0, JOptionPane.QUESTION_MESSAGE, null,
                    options, options[0]);
            switch (choix) {
                case 0:
                    testVin();
                    break;
                case 1:
                    testVentes();
                    break;
            }
        } while (choix != 2);
        System.exit(0);
    }
    
}
