package exercise2;

/**
 *
 * @author saukin
 */
public enum MESSAGES {
    
    FICHIER_NEST_PAS_TROUVE,
    WRONG_INPUT;
    
    public static String getError(int lineNo, String partie, String bureau, String nVotes) {
            return String.format("Erreur de gamme des valeurs dnas la ligne #%d : "
                    + "Partie : %s \n Bureau : %s \n Votes : %s", 
                    lineNo, partie, bureau, nVotes);
    }
}
