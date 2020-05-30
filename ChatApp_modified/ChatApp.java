//package ChatApp_updated;

import java.net.*;
import java.io.*;
//import java.util.Scanner;
//import ChatApp_updated.ChatClient;

public class ChatApp{
	
	
	public static void main(String args[]){
		//Scanner myObj = null;
		DataInputStream console = new DataInputStream(System.in);
		
		
		
		ChatClient client = new ChatClient();
		client.ChatClient("0.0.0.0", 3333); 
		String line = null;
		while(line != ".bye")
		try{
			//text = myObj.nextLine();
			BufferedReader d = new BufferedReader(new InputStreamReader(console));
			line = d.readLine();
			client.SendMessage(line);
		}catch (IOException ioe){
			ioe.printStackTrace();
		}
	}
	
	
}