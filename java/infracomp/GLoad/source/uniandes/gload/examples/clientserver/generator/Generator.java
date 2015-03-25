package uniandes.gload.examples.clientserver.generator;

import uniandes.gload.core.LoadGenerator;
import uniandes.gload.core.Task;

/**
 * GLoad Core Class - Task
 * @author Victor Guana at University of Los Andes (vm.guana26@uniandes.edu.co)
 * Systems and Computing Engineering Department - Engineering Faculty
 * Licensed with Academic Free License version 2.1
 * 
 * ------------------------------------------------------------
 * Example Class Client Server:
 * This is Generator Controller - Setup and Launch the Load 
 * Over the Server Using a Task
 * ------------------------------------------------------------
 * 
 */
public class Generator
{
	/**
	 * Load Generator Service (From GLoad 1.0)
	 */
	private LoadGenerator generator;
	
	/**
	 * Constructs a new Generator
	 */
	public Generator ()
	{
		Task work = createTask();
		int numberOfTasks = 100;
		int gapBetweenTasks = 1000;
		generator = new LoadGenerator("Client - Server Load Test", numberOfTasks, work, gapBetweenTasks);
		generator.generate();
	}
	
	/**
	 * Helper that Constructs a Task
	 */
	private Task createTask()
	{
		return new ClientServerTask();
	}
	
	/**
	 * Starts the Application
	 * @param args
	 */
	public static void main (String ... args)
	{
		@SuppressWarnings("unused")
		Generator gen = new Generator();
	}

}

