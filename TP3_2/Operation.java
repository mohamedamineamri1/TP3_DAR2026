package TP3_2;

import java.io.Serializable;
public class Operation implements Serializable {
    private static final long serialVersionUID = 1L;

    private final double operande1;
    private final double operande2;
    private final char operateur;     // + - * /
    private double resultat;
    private String erreur;            // null si tout va bien
    private int compteurGlobal;       // renseigné par le serveur

    public Operation(double operande1, char operateur, double operande2) {
        this.operande1 = operande1;
        this.operateur = operateur;
        this.operande2 = operande2;
    }

    public double getOperande1() { return operande1; }
    public double getOperande2() { return operande2; }
    public char getOperateur() { return operateur; }
    public double getResultat() { return resultat; }
    public void setResultat(double resultat) { this.resultat = resultat; }
    public String getErreur() { return erreur; }
    public void setErreur(String erreur) { this.erreur = erreur; }
    public int getCompteurGlobal() { return compteurGlobal; }
    public void setCompteurGlobal(int c) { this.compteurGlobal = c; }

    @Override
    public String toString() {
        return operande1 + " " + operateur + " " + operande2;
    }
}

