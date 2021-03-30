package labo_1_2;

import java.awt.TextArea;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author saukin
 */
public class ProdSales {

    private Product[] prodTab;

    public ProdSales() {
         this.prodTab = new Product[20];
    }
    
    
    
    private void buildProdTable() throws IOException {
        String fileName = "";
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Choisissez le fichier de base des produits");
        int choice = chooser.showOpenDialog(chooser);
        
        if (choice == JFileChooser.APPROVE_OPTION) {
            fileName = chooser.getSelectedFile().getPath();
        }
        
        Scanner reader = new Scanner(Files.newInputStream(Paths.get(fileName), StandardOpenOption.READ));
        
        int nProd = 0;
        String[] line;
        while (reader.hasNextLine()) {
            if (nProd == prodTab.length) {
                prodTab = makeTabBigger(prodTab);
            }
            line = reader.nextLine().split(";");
            prodTab[nProd++] = new Product(Integer.parseInt(line[0]), Double.parseDouble(line[1]));
        }
    }

    private Product[] makeTabBigger(Product[] prodTab) {
        Product[] tempTab = new Product[prodTab.length + 20];
        System.arraycopy(prodTab, 0, tempTab, 0, prodTab.length); 
        return tempTab;
    }
    
    private Product getProduct(int code) {
                
        return Arrays.stream(prodTab)
                .filter(e -> e != null)
                .filter(e -> e.getProdNo() == code)
                .findAny()
                .orElse(null);
        
    }
    
    private JTextArea getReport() {
        StringBuilder sb = new StringBuilder("Numero de produit\tQuantite vendu\n");
        for (Product p : prodTab) {
            if (p != null) {
                sb.append(p.getProdNo()).append("\t\t").append(p.getnVents()).append("\n");
            }
        }
        JTextArea textArea = new JTextArea(sb.toString());
        return textArea;
    }
    
    public void doSales() throws IOException {
        buildProdTable();
        int numero;
        int qte;
        double price;
        char reponse;
        Product product;
        
        do {
            numero = Integer.parseInt(JOptionPane.showInputDialog(
                    "Entrez le numéro du produit à acheter :"));
            qte = Integer.parseInt(JOptionPane.showInputDialog(
                    "Entrez la quantité désirée :"));

            product = getProduct(numero);

            if (product != null) {
                price = product.getPrice() * qte;
                JOptionPane.showMessageDialog(null, 
                        String.format("Le coût de cet achat est de %.2f $", price));
                product.raiseVents(qte);
            } else {
                JOptionPane.showMessageDialog(null, "No de produit erroné");
            }

            reponse = JOptionPane.showInputDialog(
                    "Avez-vous un autre client à traiter O/N ?").charAt(0);
            reponse = Character.toUpperCase(reponse);
        } while (reponse == 'O');
        
        JOptionPane.showMessageDialog(null, getReport(), null, JOptionPane.PLAIN_MESSAGE, null);
    }
    
}
