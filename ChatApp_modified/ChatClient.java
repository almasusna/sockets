//package ChatApp_updated;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ChatClient{
	private Socket socket = null;
	private Scanner myObj = null;
	private DataInputStream console = null;
	private DataOutputStream streamOut = null;
	

    public void ChatClient(String serverName, int serverPort){
    	try{
			Socket socket = new Socket(serverName, serverPort);
			Scanner myObj = new Scanner(System.in);
			
			streamOut = new DataOutputStream(socket.getOutputStream());
			
		} catch(IOException ioe){
			ioe.printStackTrace();
		}
		
	}
	public void SendMessage(String message){
		try{
		streamOut.writeUTF(message);
		streamOut.flush();
		} 
		catch (IOException ioe){
			ioe.printStackTrace();
		}
	}
}
