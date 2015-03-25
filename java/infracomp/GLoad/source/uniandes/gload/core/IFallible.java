package uniandes.gload.core;

/**
 * GLoad Core Class - Task
 * Represents the interface characterization of fail or success for a task
 * @author Victor Guana at University of Los Andes (vm.guana26@uniandes.edu.co)
 * Systems and Computing Engineering Department - Engineering Faculty
 * Licensed with Academic Free License version 2.1
 */
public interface IFallible 
{
	/**
	 * Called in the case of report a failure
	 */
	public void fail();
	
	/**
	 * Called in the case of report the success
	 */
	public void success();

}
