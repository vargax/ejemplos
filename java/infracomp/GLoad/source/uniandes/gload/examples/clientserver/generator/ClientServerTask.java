package uniandes.gload.examples.clientserver.generator;

import uniandes.gload.core.Task;
import uniandes.gload.examples.clientserver.Client;

/**
 * GLoad Core Class - Task
 * @author Victor Guana at University of Los Andes (vm.guana26@uniandes.edu.co)
 * Systems and Computing Engineering Department - Engineering Faculty
 * Licensed with Academic Free License version 2.1
 * 
 * ------------------------------------------------------------
 * Example Class Client Server:
 * This Class Represents the task that we want to generate in a concurrent way
 * ------------------------------------------------------------
 * 
 */
public class ClientServerTask extends Task
{

	@Override
	public void execute() 
	{
		// TODO Auto-generated method stub
		Client client = new Client();
		client.sendMessageToServer("Hi! i'm a client");
		client.waitForMessageFromServer();
		client.sendMessageToServer("EOT");
		
	}

	@Override
	public void fail() 
	{
		// TODO Auto-generated method stub
		System.out.println(Task.MENSAJE_FAIL);
		
	}

	@Override
	public void success() 
	{
		// TODO Auto-generated method stub
		System.out.println(Task.OK_MESSAGE);
		
	}

}
