import java.io.*;
import java.net.*;

public class Server_multiple_client_at_a_time {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("Server started. Waiting for clients...");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("New client connected: " + socket.getInetAddress());

            // Start a new thread for this client
            ClientHandler handler = new ClientHandler(socket);
            new Thread(handler).start();
        }

    }
}

class ClientHandler implements Runnable {
    private Socket socket;

    ClientHandler(Socket socket) {
        this.socket = socket;
    }

    

    public void run() {
        try {
            BufferedReader inputFromClient = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter outputToClient = new PrintWriter(socket.getOutputStream(), true);

            String msgFromClient;
            String msgToClient = "";

            while ((msgFromClient = inputFromClient.readLine()) != null) {
                if (msgFromClient.equalsIgnoreCase("quit")) {
                    break;
                } else if (msgFromClient.split(" ")[0].equalsIgnoreCase("files")) {
                    File folder = new File(
                            "E:\\UBIT\\2nd semester\\OOP-Java\\TCP-connection\\file-commands-client-server\\files");
                    File[] filesList = folder.listFiles();

                    StringBuilder filesNamesList = new StringBuilder();
                    for (File file : filesList) {
                        filesNamesList.append(file.getName()).append("\n");
                    }
                    msgToClient = filesNamesList.toString();
                } else if (msgFromClient.split(" ")[0].equalsIgnoreCase("get")) {
                    String fileName = msgFromClient.split(" ")[1];
                    BufferedReader br = new BufferedReader(new FileReader(
                            "E:\\UBIT\\2nd semester\\OOP-Java\\TCP-connection\\file-commands-client-server\\files\\"
                                    + fileName));

                    String line;
                    StringBuilder fileContent = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        fileContent.append(line).append("\n");
                    }
                    br.close();
                    msgToClient = fileContent.toString();
                }

                System.out.println("Client " + socket.getInetAddress() + ": " + msgFromClient);
                outputToClient.println(msgToClient);
                outputToClient.println("END");
                msgToClient = "";
            }

            socket.close();
            System.out.println("Client disconnected: " + socket.getInetAddress());

        } catch (Exception e) {
            System.out.println("Error handling client: " + e.getMessage());
        }
    }
}