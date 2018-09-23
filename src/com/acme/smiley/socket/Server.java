package com.acme.smiley.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Smiley socket server.
 *
 * This server listen on port {@value #PORT}.
 */
public class Server {

    /**
     * Listening port.
     */
    public static final int PORT = 6900;

    /**
     * Main method.
     *
     * @param args  program arguments
     */
    public static void main(String[] args) {
        System.out.printf("Starting socket server on port %d.\n", PORT);
        try (ServerSocket server = new ServerSocket(PORT)) {
            while (!Thread.interrupted()) {
                try (
                        Socket socket = server.accept();
                        Scanner scanner = new Scanner(socket.getInputStream());
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                    System.out.printf("Connection established with %s.", socket.getInetAddress());
                    String line;
                    do {
                        line = scanner.nextLine();
                        out.println(line.replace(":)", "☺"));
                    } while (!line.toLowerCase().startsWith("bye"));
                    System.out.printf("Closing connection with %s...\n", socket.getInetAddress());
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
