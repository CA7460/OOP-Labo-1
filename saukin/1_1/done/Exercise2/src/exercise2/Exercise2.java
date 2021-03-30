package exercise2;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author saukin
 */
public class Exercise2 {
    
    private static final int NB_PARTIS = 3;
    private static final int NB_BUREAUX = 10;
    private final int tabTotParti[] = new int[NB_PARTIS];
    private final int tabTotBureau[] = new int[NB_BUREAUX];
    private final Map<Integer, List<String>> errors = new HashMap<>();

    public Exercise2(String fileName) {
        getDataFromFile(fileName);
    }
    
    private void getDataFromFile(String fileName) {
        String line;
        int partie;
        int bureau;
        int nVotes;
        String[] values;
        
        try (Scanner reader = new Scanner(new FileReader(fileName))) {
            int linesCounter = 1;
            while(reader.hasNextLine() && 
                    ((line = reader.nextLine()).trim().length() > 0)) {
                values = line.split(" ");
                bureau = getIntValue(values[0]) - 1;
                partie = getIntValue(values[1]) - 1;
                nVotes = getIntValue(values[2]);
                if (arePartieBureauVotesInRange(partie, bureau, nVotes)) {
                    this.tabTotBureau[bureau] += nVotes;
                    this.tabTotParti[partie] += nVotes;
                } else {
                    List<String> errorLine = new ArrayList<>();
                    errorLine.addAll(Arrays.asList(values));
                    this.errors.put(linesCounter, errorLine);
                }
                linesCounter++;
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, MESSAGES.FICHIER_NEST_PAS_TROUVE);
        }
    }
    
    private int getIntValue(String strValue) {
        try {
            return Integer.parseInt(strValue);
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }
    
    private static boolean arePartieBureauVotesInRange(int partie, int bureau, int nVotes) {
        return (partie >= 0 && partie < NB_PARTIS)  
                && (bureau >= 0 && bureau < NB_BUREAUX)
                && nVotes > 0;
    }
    
    private String getData(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(String.format("%d\t\t%d\n", i+1, array[i]));
        }
        return sb.toString();
    }
    
    
    public void showResults() {
        JTextArea sortie = new JTextArea();
        sortie.append("PARTI\tTOTAL DES VOTES\n");
        sortie.append(getData(this.tabTotParti));
        sortie.append("\nBUREAU\tTOTAL DES VOTANTS\n");
        sortie.append(getData(this.tabTotBureau));
        
        if (this.errors.size() > 0) {
            sortie.append("\nDes erreurs se sont produites: \n");
            for (Map.Entry<Integer, List<String>> entry : this.errors.entrySet()) {
                sortie.append(String.format("Line %s: bureau -> %s, partie -> %s, "
                        + "votes -> %s \n", entry.getKey()
                                          , entry.getValue().get(0)
                                          , entry.getValue().get(1)
                                          , entry.getValue().get(2)));
            }
            
        }
        
        JOptionPane.showMessageDialog(null, sortie,
                "RÉSULTATS DE L'ÉLECTION", JOptionPane.PLAIN_MESSAGE);
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
    
    
    public static void main(String args[]) {
        
        String fileName = getFileName();
        if (fileName.length() > 0) {
            Exercise2 ex2 = new Exercise2(fileName);
            ex2.showResults();
        }
        System.exit(0);
    }
}
