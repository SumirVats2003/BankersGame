package server;

import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private GameServer gameServer;
    private Player player;

    // Constructor
    public ClientHandler(Socket socket, GameServer gameServer) {
        this.clientSocket = socket;
        this.gameServer = gameServer;

        try {
            // Create input and output streams for communication
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Run method to handle client communication
    @Override
    public void run() {
        try {
            // Get the username from the client
            String username = getUsername();
            System.out.println("User " + username + " connected.");

            player = new Player(username, this);

            gameServer.addPlayerToRoom(player);

            out.println("Welcome to the chat, " + username + "!");
            out.println("Type Your Message");
            String inputLine;

            // Continue receiving messages from the client
            while ((inputLine = in.readLine()) != null) {
                System.out.println("[" + username + "]: " + inputLine);

//                // Broadcast the message to all clients
//                Server.broadcast("[" + username + "]: " + inputLine, this);

                //Broadcast the method to all room member
                Server.sendMessageToRoom("[" + username + "]: " + inputLine, player);
            }

            // Remove the client handler from the list
            Server.remove(this);

            // Close the input and output streams and the client socket
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get the username from the client
    private String getUsername() throws IOException {
        out.println("Enter your username:");
        return in.readLine();
    }

    public void sendMessage(String message) {
        out.println(message);
        out.println("Type Your Message");
    }

    public Player getPlayer() {
        return player;
    }
}
