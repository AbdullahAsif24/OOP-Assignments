package Threading;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_with_oneclient {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("Server is waiting for connection...");

        while (true) {
            Socket socket = serverSocket.accept();

            ServerThread th = new ServerThread(socket);

            th.start();

            th.join();
            System.out.println("Ready for next client...\n");
        }

    }
}

class ServerThread extends Thread {
    private Socket socket;

    ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        System.out.println("Client connected!");

        try {

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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}