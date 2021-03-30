package exercise1;

/**
 *
 * @author saukin
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import java.text.*;
import java.util.Scanner;

public class Exercice1 {

    public static void main(String... args) throws IOException {
        final int NB_MAX_PROD = 20;
        int tabNoProd[] = new int[NB_MAX_PROD];
        double tabPrixProd[] = new double[NB_MAX_PROD];
        int tabQte[] = new int[NB_MAX_PROD];
        int nbProd; // le nombre de produits contenus dans le fichier

        if ((nbProd = batirTableaux(tabNoProd, tabPrixProd)) > 0)  {
            traiterLesClients(tabNoProd, tabPrixProd, tabQte, nbProd);
            afficherResultats(tabNoProd, tabQte, nbProd);
        }
        System.exit(0);

    }

    static int batirTableaux(int[] tabProd, double[] tabPrix) throws FileNotFoundException {
        int nLines = 0;
        String line;
        String[] lineElems;
        String fileName;

        if (!(fileName = getFileName()).isEmpty()) {
            try (Scanner reader = new Scanner(new FileReader(fileName))) {
                while (reader.hasNextLine()
                        && ((line = reader.nextLine()).trim().length() > 0)) {
                    lineElems = line.split(";");
                    tabProd[nLines] = Integer.parseInt(lineElems[0]);
                    tabPrix[nLines] = Double.parseDouble(lineElems[1]);
                    nLines++;
                }
            }
        }
        return nLines;
    }

    private static String getFileName() {
        String fileName = "";
        JFileChooser fileChoser = new JFileChooser();
        int choice = fileChoser.showOpenDialog(fileChoser);

        if (choice == JFileChooser.APPROVE_OPTION) {
            fileName = fileChoser.getSelectedFile().getPath();
        }
        return fileName;
    }

    private static void traiterLesClients(int[] tabNoProd, double[] tabPrix,
            int[] tabQteTotale, int nbProd) {
        DecimalFormat cash = new DecimalFormat("0.00 $");
        int numero;
        int qte;
        int posiProd; // position du numéro dans le tableau tabNoProd
        double cout;
        char reponse;

        do {
            numero = Integer.parseInt(JOptionPane.showInputDialog(
                    "Entrez le numéro du produit à acheter :"));
            qte = Integer.parseInt(JOptionPane.showInputDialog(
                    "Entrez la quantité désirée :"));

            posiProd = rechercher(tabNoProd, nbProd, numero);

            if (posiProd != -1) {
                cout = tabPrix[posiProd] * qte;
                JOptionPane.showMessageDialog(null,
                        "Le coût de cet achat est de " + cash.format(cout));
                tabQteTotale[posiProd] = tabQteTotale[posiProd] + qte;
            } else {
                JOptionPane.showMessageDialog(null, "No de produit erroné");
            }

            reponse = JOptionPane.showInputDialog(
                    "Avez-vous un autre client à traiter O/N ?").charAt(0);
            reponse = Character.toUpperCase(reponse);
        } while (reponse == 'O');
    }

    static int rechercher(int tab[], int nbEl, int valeurCherchee) {
        int posi = -1;
        boolean trouve = false;
        for (int i = 0; i < nbEl && !trouve; i++) {
            if (tab[i] == valeurCherchee) {
                posi = i;
                trouve = true;
            }
        }
        return posi;
    }

    private static void afficherResultats(int[] tabNoProd, int[] tabQteTotale, int nbProd) {
        String prodNo = "Numero du produit";
        String qtyTotal = "Quantite totale";
        String header = String.format("%20s\t%15s", prodNo, qtyTotal);
        String line = "\n%20d\t\t%15d";
        StringBuilder content = new StringBuilder(header);

        for (int i = 0; i < nbProd; i++) {
            content.append(String.format(line, tabNoProd[i], tabQteTotale[i]));
        }

        JTextArea textArea = new JTextArea(content.toString());

        JOptionPane.showMessageDialog(null, textArea, "Liste de ventes",
                JOptionPane.PLAIN_MESSAGE);
    }
}
