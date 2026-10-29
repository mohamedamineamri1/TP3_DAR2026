package TP3_1;
import java.io.*;
import java.net.Socket;
public class ClientProcess extends Thread {
    private final Socket socket;
    private final int numero;
    public ClientProcess(Socket socket, int numero) {
        this.socket = socket;
        this.numero = numero;
    }
    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            out.println("Bienvenue ! Vous êtes le client n°" + numero
                    + " (tapez 'bye' pour quitter)");
            String ligne;
            while ((ligne = in.readLine()) != null) {
                System.out.println("Client n°" + numero + " dit : " + ligne);
                if (ligne.equalsIgnoreCase("bye")) {
                    out.println("Au revoir client n°" + numero);
                    break;
                }
                out.println("Echo serveur -> " + ligne.toUpperCase());
            }
        } catch (IOException e) {
            System.err.println("Client n°" + numero + " : erreur " + e.getMessage());
        } finally {
            try { socket.close(); } catch (IOException ignored) {}
            System.out.println("Client n°" + numero + " déconnecté.");
        }
    }
}
