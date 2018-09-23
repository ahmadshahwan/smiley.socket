package com.acme.smiley.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Smiley socket client.
 */
public class Client {
    
    /**
     * Main method that starts the client.
     * 
     * @param args  program arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome to Smiley Socket client.");
        try (
                Socket socket = new Socket(InetAddress.getLoopbackAddress(), Server.PORT);
                Scanner reader = new Scanner(socket.getInputStream());
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                Scanner scanner = new Scanner(System.in)) {
            writer.println(scanner.nextLine());
            while (reader.hasNext()) {
                System.out.println(reader.nextLine());
                writer.println(scanner.nextLine());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
