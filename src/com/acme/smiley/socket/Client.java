package com.acme.smiley.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                Scanner scanner = new Scanner(System.in)) {
            String line;
            do {
                writer.println(scanner.nextLine());
                line = reader.readLine();
                System.out.println(line != null ? line : "Session closed.");
            } while (line != null);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
