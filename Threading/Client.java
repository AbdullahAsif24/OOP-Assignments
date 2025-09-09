package Threading;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8888);
        System.out.println("Connected to server!");

        BufferedReader inputFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter outputToServer = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

        // Thread to listen for server messages
        Thread receiveThread = new Thread(() -> {
            try {
                String msgFromServer;
                while ((msgFromServer = inputFromServer.readLine()) != null) {
                    if (msgFromServer.equalsIgnoreCase("exit")) {
                        System.out.println("Server closed connection.");
                        System.exit(0);
                    }
                    System.out.println("\nServer: " + msgFromServer);
                    System.out.print("You: "); // prompt again
                }
            } catch (IOException e) {
                System.out.println("Disconnected from server.");
            }
        });
        receiveThread.start();

        // Main thread: send messages
        String msgToServer;
        while (true) {
            System.out.print("You: ");
            msgToServer = keyboard.readLine();
            outputToServer.println(msgToServer);

            if (msgToServer.equalsIgnoreCase("exit")) {
                break;
            }
        }

        socket.close();
        System.out.println("Client closed.");
    }
}
