import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UDPClient {
    public static void main(String[] args) {
        String hostname = "localhost"; // Server address
        int port = 9876; // Server port

        try (DatagramSocket socket = new DatagramSocket()) { // Create a DatagramSocket for sending and receiving data
            InetAddress address = InetAddress.getByName(hostname); // Get the server address

            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in)); // Reader to read input from the console
            byte[] buffer = new byte[1024]; // Buffer to store incoming data

            System.out.println("Connected to the server. Type messages to send:");

            while (true) { // Infinite loop to keep the client running
                // Send message to the server
                System.out.print("Enter message to send: "); // Prompt the client user to enter a message
                String text = consoleReader.readLine(); // Read the message from the console
                byte[] sendData = text.getBytes(); // Convert the message to a byte array
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port); // Create a DatagramPacket to send the message
                socket.send(sendPacket); // Send the message to the server

                // Receive response from the server
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length); // Create a DatagramPacket to receive data
                socket.receive(receivePacket); // Receive data from the server
                String response = new String(receivePacket.getData(), 0, receivePacket.getLength()); // Convert the received data to a string
                System.out.println("Received from server: " + response); // Print the server's response
            }
        } catch (Exception ex) { // Catch any exceptions that occur
            System.out.println("Client exception: " + ex.getMessage()); // Print the exception message
            ex.printStackTrace(); // Print the stack trace of the exception
        }
    }
}
