package uniandes.gload.core;

/**
 * GLoad Core Class - Task
 * Represents the task to execute in a command pattern
 * @author Victor Guana at University of Los Andes (vm.guana26@uniandes.edu.co)
 * Systems and Computing Engineering Department - Engineering Faculty
 * Licensed with Academic Free License version 2.1
 */
public abstract class Task implements IFallible
{
	/**
	 * Complete Running and Execution Constant 
	 */
	public static final String OK_MESSAGE = "OK_TEST";
	
	/**
	 * Incomplete or Fail Running and Execution Constant 
	 */
	public static final String MENSAJE_FAIL = "FAIL_TEST";
	
	/**
	 * Executes the Task
	 */
	public abstract void execute();


}
