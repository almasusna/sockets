//package com.company;

import java.net.*;
import java.io.*;

public class ChatClient {
    private Socket socket = null;
    private DataInputStream console = null;
    private DataOutputStream streamOut = null;

    public ChatClient (String serverName, int serverPort) {
        // -------------------------------------------------------------------------
        // Create client socket and connect to server
        // Create two streams: console(user input) and streamOut(send console input to server)

        try{
            socket = new Socket(serverName, serverPort);
            System.out.println("Connected "+ socket);
        }
        catch (UnknownHostException uhe){
            uhe.printStackTrace();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        String line = "";
        while (!line.equals(".bye"))
        try{ // Send input from the user to the Server
		    console = new DataInputStream(System.in);
        	streamOut = new DataOutputStream(socket.getOutputStream());
			
            BufferedReader d = new BufferedReader(new InputStreamReader(console));
            line = d.readLine();
            streamOut.writeUTF(line);
            streamOut.flush();
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    // -------------------------------------------------------------------------
    // Get input from the user
    // Get message from the connected Server
  //  public void start() throws IOException{

    //}
    // -------------------------------------------------------------------------
    // Close streams and socket
    public void stop(){
        try{
            if (console != null) console.close();
            if (streamOut != null) streamOut.close();
            if (socket != null) socket.close();
        }
        catch (IOException ioe){
            System.out.println("Error closing...");
        }
    }
    // -------------------------------------------------------------------------
    public static void main(String args[]){
        ChatClient client = null;
        if (args.length != 2)
            System.out.println("Usage: java Client host port");
        else
            client = new ChatClient(args[0], Integer.parseInt(args[1]));
    }
}
