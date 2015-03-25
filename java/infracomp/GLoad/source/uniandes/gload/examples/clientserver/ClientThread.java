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
 * This Class Represents a Client Thread of a connection from a
 * client in the server.
 * ------------------------------------------------------------
 */ 
public class ClientThread extends Thread implements Runnable
{
	/**
	 * Socket Connection of the Client Side
	 */
	private Socket client;
	
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
	 * Constructs a new Thread for a Connection of a Client into the Server
	 * @param clientP
	 */
	public ClientThread(Socket clientP)
	{
		client = clientP;
		try 
		{
			inS = client.getInputStream();
			outS = client.getOutputStream();
			in = new BufferedReader(new InputStreamReader(inS));
			out = new PrintWriter(outS, true);
		} 
		catch (Exception e) {
			System.out.println("Fail Opening the Client Socket: " + e.getMessage());
		}
	}
	
	/**
	 * Run the main operation of the Thread
	 */
	public void run()
	{
		try 
		{
			boolean connected = true;
			while(connected)
			{
				String clientMessage = in.readLine();
				System.out.println("Message From Client Recived: " + clientMessage);
				out.println("ACK");
				if(clientMessage.equalsIgnoreCase("EOT"))// Listen until End Of Transmision is Recived
				{
					connected = false;
				}
			}
			
		} 
		catch (IOException e) 
		{
			System.out.println("Fail to Read the Message from the Client: " + e.getMessage());
		}
	}

}
