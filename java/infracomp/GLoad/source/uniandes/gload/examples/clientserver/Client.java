package uniandes.gload.examples.clientserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * GLoad Core Class - Task
 * @author Victor Guana at University of Los Andes (vm.guana26@uniandes.edu.co)
 * Systems and Computing Engineering Department - Engineering Faculty
 * Licensed with Academic Free License version 2.1
 * 
 * ------------------------------------------------------------
 * Example Class Client Server:
 * This Class Represents the Client Side of the Application
 * ------------------------------------------------------------
 * 
 */
public class Client
{
	/**
	 * Socket Connection of the Client Side
	 */
	private Socket socket;
	
	/**
	 * Stream IN Information
	 */
	private  InputStream inS;
	
	/**
	 * Stream OUT Information
	 */
	private OutputStream outS;
	
	/**
	 * BufferReader IN
	 */
	private BufferedReader in;
	
	/**
	 * Print Writer OUT
	 */
	private PrintWriter out;
	
	/**
	 * Constructs a new Client
	 */
	public Client()
	{
		try 
		{
			socket = new Socket("localhost", 9999);
			inS = socket.getInputStream();
			outS = socket.getOutputStream();
			in = new BufferedReader(new InputStreamReader(inS));
			out = new PrintWriter(outS, true);
		} 
		catch (Exception e) {
			System.out.println("Fail Opening de Client Socket: " + e.getMessage());
		}
	}
	
	/**
	 * Sends a Message from the Client to the Server
	 * @param message
	 */
	public synchronized void sendMessageToServer(String message)
	{
		out.println(message);
	}
	
	/**
	 * Listen for a Message from the Server
	 */
	public synchronized void waitForMessageFromServer()
	{
		try 
		{
			String answer =in.readLine();
			System.out.println("Client - Message: " + answer);
		}
		catch (IOException e) 
		{
			System.out.println("Fail to Listen ACK from Server: " + e.getMessage());
		}
	}
	
	/**
	 * Launch the client side of the example
	 */
	public static void main (String ... args)
	{
		Client client = new Client();
		client.sendMessageToServer("Hi! i'm a client");
		client.waitForMessageFromServer();
		client.sendMessageToServer("EOT");
		client.waitForMessageFromServer();
	}


}
