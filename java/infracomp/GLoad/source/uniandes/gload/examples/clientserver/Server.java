package uniandes.gload.examples.clientserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * GLoad Core Class - Task
 * @author Victor Guana at University of Los Andes (vm.guana26@uniandes.edu.co)
 * Systems and Computing Engineering Department - Engineering Faculty
 * Licensed with Academic Free License version 2.1
 * 
 * ------------------------------------------------------------
 * Example Class Client Server:
 * This Class Represents the Server Side of the Application
 * ------------------------------------------------------------
 * 
 */
public class Server
{
	/**
	 * Listen Server Socket
	 */
	private ServerSocket server;
	
	/**
	 * Constructs a new Server
	 */
	public Server()
	{
		try 
		{
			server = new ServerSocket(9999); // Listen at the 9999 port
		}
		catch (IOException e) 
		{
			System.out.println("Fail Opening the Server t Socket: " + e.getMessage());
		}
		listenClients();
	}
	
	/**
	 * Wait until a new Client Message Arrive
	 */
	public void listenClients()
	{
		System.out.println("Server Running ...");
		try 
		{
			while(true)
			{
				Socket clientScoket = server.accept();
				System.out.println("Conection Accepted!");
				ClientThread ct = new ClientThread(clientScoket);
				ct.run();
			}
			
		} 
		catch (IOException e) 
		{
			System.out.println("Fail Connecting to the Client: " + e.getMessage());
		}
	}
	
	/**
	 * Launcher of the Server Side of the Application 
	 * @param args
	 */
	public static void main (String ... args)
	{
		@SuppressWarnings("unused")
		Server serv = new Server();
	}

}
