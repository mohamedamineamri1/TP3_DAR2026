package TP3_2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {
    public static final int PORT = 1235;
    public static void main(String[] args) {
        int numeroClient = 0;
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Serveur calculatrice demarre sur le port " + PORT + "...");
            while (true) {
                Socket socket = serverSocket.accept();
                numeroClient++;
                System.out.println("Client n°" + numeroClient + " connecte, IP : "
                        + socket.getRemoteSocketAddress());
                new ClientProcess(socket, numeroClient).start();
            }
        } catch (IOException e) {
            System.err.println("Erreur serveur : " + e.getMessage());
        }
    }
}
