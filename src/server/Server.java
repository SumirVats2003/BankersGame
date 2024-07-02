package server;

import java.io.IOException;
import java.net.*;

public class Server {
    final int PORT = 5000;
    ServerSocket socket = new ServerSocket(PORT);

    public Server() throws IOException {
    }
}
