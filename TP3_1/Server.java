package TP3_1;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {
    public static final int PORT =1234;
    public static void main(String[] args) {
        int numeroClient=0;
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Serveur multi-thread démarré sur le port " + PORT );
            while (true) {
                Socket socket = serverSocket.accept();    
                numeroClient++;
                System.out.println("Client n°" + numeroClient + " connecté, IP : "
                        + socket.getRemoteSocketAddress());
                new ClientProcess(socket,numeroClient).start(); 
            }
        } catch (IOException e) {
            System.err.println("Erreur serveur : " + e.getMessage());
        }
    }
}

