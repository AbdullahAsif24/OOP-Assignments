package Threading;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server_multiple_client_at_a_time {
    public static ArrayList<PrintWriter> clientsOutputList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("Server started. Waiting for clients...");

        // broadcast to client
        ServerSendMsgThread sth = new ServerSendMsgThread();
        sth.start();

        // clients connect
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("New client connected: " + socket.getInetAddress());

            // Start a new thread for this client
            ServerThread th = new ServerThread(socket);
            th.start();

        }

    }
}



class ServerSendMsgThread extends Thread {
    public void run() {
        try {
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            String msgToClients;

            while (true) {
                msgToClients = keyboard.readLine();
                broadcast(msgToClients);

                if (msgToClients.equalsIgnoreCase("exit")) {
                    System.out.println("Shutting down server...");
                    System.exit(0);
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static void broadcast(String msg) {
        for (PrintWriter writer : Server_multiple_client_at_a_time.clientsOutputList) {
            writer.println(msg);
        }
    }
}



class ServerThread extends Thread {
    private Socket socket;

    ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outputToClient = new PrintWriter(socket.getOutputStream(), true);


            Server_multiple_client_at_a_time.clientsOutputList.add(outputToClient);
            
            String msgFromClient;
            
            while (true) {
                msgFromClient = inputFromClient.readLine();
                if (msgFromClient.equalsIgnoreCase("exit"))
                break;
                
                System.out.println(ServerThread.currentThread().getName() + msgFromClient);
                
            }
            
            socket.close();
            Server_multiple_client_at_a_time.clientsOutputList.remove(outputToClient);

        } catch (Exception e) {
            System.out.println("Error handling client: " + e.getMessage());
        }
    }
}