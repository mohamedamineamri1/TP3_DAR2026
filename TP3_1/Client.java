package TP3_1;
import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
public class Client {
    public static void main(String[] args) {
        String hote = (args.length > 0) ? args[0] : "localhost";
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(InetAddress.getByName(hote), Server.PORT), 5000);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
            System.out.println(in.readLine()); // message de bienvenue avec le numéro
            String msg;
            while ((msg = clavier.readLine()) != null) {
                out.println(msg);
                System.out.println(in.readLine());
                if (msg.equalsIgnoreCase("bye")) break;
            }
        } catch (IOException e) {
            System.err.println("Erreur client : " + e.getMessage());
        }
    }
}
