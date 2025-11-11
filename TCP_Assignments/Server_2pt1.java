package TCP_Assignments;

import java.io.*;
import java.net.*;

public class Server_2pt1 {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("Server is waiting for connection...");

        while (true) {

            Socket socket = serverSocket.accept();
            System.out.println("Client connected!");

            BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outputToClient = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            String msgFromClient, msgToClient;

            while (true) {
                msgFromClient = inputFromClient.readLine();
                if (msgFromClient.equalsIgnoreCase("exit"))
                    break;

                System.out.println("Client: " + msgFromClient);

                System.out.print("You: ");
                msgToClient = keyboard.readLine();
                outputToClient.println(msgToClient);

                if (msgToClient.equalsIgnoreCase("exit"))
                    break;
            }

            socket.close();
        }
    }
}