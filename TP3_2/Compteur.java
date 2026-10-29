package TP3_2;

public class Compteur {
    private static int valeur = 0;
    private static final Object verrou = new Object();

    private Compteur() {}
    public static int incrementer() {
        synchronized (verrou) {
            valeur++;
            return valeur;
        }
    }
}
