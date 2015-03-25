package uniandes.gload.core;

import java.util.Date;

/**
 * GLoad Core Class - Task
 * Represents the Load Unit - Atomic Unit of Load in the Framework
 * @author Victor Guana at University of Los Andes (vm.guana26@uniandes.edu.co)
 * Systems and Computing Engineering Department - Engineering Faculty
 * Licensed with Academic Free License version 2.1
 */
public class LoadUnit implements Runnable
{
	/**
	 * Task to Execute
	 */
	private Task command;
	
	/**
	 * ID of the load Unit
	 */
	private int id;
	
	/**
	 * Extra Waiting Time Gap (Differential Between LoadUnits)
	 */
	private long extraTimeGap;
	
	/**
	 * Indicates if is necessary to wait until synchronization
	 */
	private boolean sync;
	
	/**
	 * Constructs a new LoadUnit
	 * @param commandP
	 * @param extraTimeGapP
	 */
	public LoadUnit(Task commandP, int idP, long extraTimeGapP, boolean syncP)
	{
		command = commandP;
		id = idP;
		extraTimeGap = extraTimeGapP;
		sync = syncP;
	}
	
	@Override
	public void run() 
	{
		if(sync)
		{
			waitUntil();
		}
		command.execute();
		System.out.println("[LoadUnit " + id + "] [Executed at: "+ new Date(System.currentTimeMillis())+"]" );
	}
	
	/**
	 * Wait for Synchronization for Execution
	 */
	public void waitUntil()
	{
		long born = System.currentTimeMillis();
		long waitMl = born + LoadGenerator.SYNC_GAP + extraTimeGap;
		
		Date wait = new Date (waitMl);
		
		System.out.println("[LoadUnit"+ id +"] [Waiting Until Sync: "+ wait.toString()+"**]" );

		boolean isTheTime = false;
		while(!isTheTime)
		{				
			if(new Date(System.currentTimeMillis()).toString().equals(wait.toString()))
			{
				isTheTime = true;
			}
		}
	}
	


}
