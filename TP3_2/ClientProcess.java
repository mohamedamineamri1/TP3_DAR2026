package TP3_2;
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
        try {
            // Ordre important : le flux de sortie d'abord (et flush), puis celui d'entrée
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out.writeInt(numero);   // numéro d'ordre envoyé au client
            out.flush();

            while (true) {
                Operation op = (Operation) in.readObject();   // objet reçu
                calculer(op);
                int c = Compteur.incrementer();               // accès synchronisé
                op.setCompteurGlobal(c);
                System.out.println("[Client n°" + numero + "] " + op + " = "
                        + (op.getErreur() == null ? op.getResultat() : op.getErreur())
                        + " | compteur global = " + c);
                out.reset();
                out.writeObject(op);                          // objet renvoyé
                out.flush();
            }
        } catch (EOFException e) {
            // client déconnecté proprement
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Client n°" + numero + " : " + e.getMessage());
        } finally {
            try { socket.close(); } catch (IOException ignored) {}
            System.out.println("Client n°" + numero + " déconnecté.");
        }
    }
    private void calculer(Operation op) {
        double a = op.getOperande1(), b = op.getOperande2();
        switch (op.getOperateur()) {
            case '+': op.setResultat(a + b); break;
            case '-': op.setResultat(a - b); break;
            case '*': op.setResultat(a * b); break;
            case '/':
                if (b == 0) op.setErreur("Division par zéro");
                else op.setResultat(a / b);
                break;
            default: op.setErreur("Opérateur inconnu : " + op.getOperateur());
        }
    }
}
