package TP3_2;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
public class Client {
    public static void main(String[] args) {
        String hote = (args.length > 0) ? args[0] : "localhost";
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(InetAddress.getByName(hote), Server.PORT), 5000);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            System.out.println("Connecte. Vous êtes le client n°" + in.readInt());
            try (Scanner sc = new Scanner(System.in)) {
				while (true) {
				    System.out.print("Opération (ex: 12 + 5) ou 'q' pour quitter : ");
				    String ligne = sc.nextLine().trim();
				    if (ligne.equalsIgnoreCase("q")) break;
				    String[] t = ligne.split("\\s+");
				    if (t.length != 3) { System.out.println("Format invalide."); continue; }
				    try {
				        Operation op = new Operation(Double.parseDouble(t[0]), t[1].charAt(0),
				                Double.parseDouble(t[2]));
				        out.reset();
				        out.writeObject(op);
				        out.flush();
				        Operation rep = (Operation) in.readObject();
				        if (rep.getErreur() != null)
				            System.out.println("Erreur : " + rep.getErreur());
				        else
				            System.out.println("Résultat = " + rep.getResultat()
				                    + "  (opérations traitées par le serveur : " + rep.getCompteurGlobal() + ")");
				    } catch (NumberFormatException e) {
				        System.out.println("Nombres invalides.");
				    }
				}
			}
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur client : " + e.getMessage());
        }
    }
}
