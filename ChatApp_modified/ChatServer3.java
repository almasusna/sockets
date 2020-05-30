//package com.company;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//import com.company.ChatServerThread

public class ChatServer3 implements Runnable{
    private ServerSocket server = null;
    private Thread thread = null;
    private ChatServerThread client = null;
    // -----------------------------------------------------------------
    // Creates server socket and starts a thread
    public ChatServer3(int port)
    {
        try{
            System.out.println("Binding to port " + port + ", please wait ...");
            server = new ServerSocket(port);
            System.out.println("Server started: "+ server);
            start();
        }
        catch (IOException ioe) {
            System.out.println("Can't create server socket: "+ioe);
        }
    }
    // ------------------------------------------------------------------
    // Executed when thread is started
    public void run(){
        while (thread != null){
            try{
                System.out.println("Waiting for a client ...");
                addThread(server.accept());
            }
            catch (IOException ie){
                System.out.println("Can't accept the clinet:" + ie);
            }
        }
    }
    public void addThread(Socket socket){
        System.out.println("Client accepted: " + socket);
        client = new ChatServerThread(this, socket);
        try{
            client.open();
            client.start();
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
    // ------------------------------------------------------------------------
    // Executes run function
    public void start(){
        if (thread == null){
            thread = new Thread(this);
            thread.start();
        }
    }

    public static void main(String args[]){
        ChatServer3 server = null;
        if (args.length != 1)
            System.out.println("Usage: java ChatServer2 port");
        else
            server = new ChatServer3(Integer.parseInt(args[0]));
    }

}


class ChatServerThread extends Thread {
    private Socket socket = null;
    private ChatServer3 server = null;
    private int ID = -1;
    private DataInputStream streamIn = null;

    public ChatServerThread(ChatServer3 _server, Socket _socket){
        server = _server; socket = _socket; ID = socket.getPort();
    }
    public void run(){
        System.out.println("Server Thread: " + ID + " running.");
        while (true){
            try{
                System.out.println(streamIn.readUTF());
            }
            catch (IOException ioe){
            }
        }
    }
    public void open() throws IOException{
        streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
    }
    public void close() throws IOException{
        if (socket != null) socket.close();
        if (streamIn != null) socket.close();
    }
}

