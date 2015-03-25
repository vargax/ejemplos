package uniandes.gload.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * GLoad Core Class - Task
 * Represents the main class where the Load Units are executed. Controller of the Framework
 * @author Victor Guana at University of Los Andes (vm.guana26@uniandes.edu.co)
 * Systems and Computing Engineering Department - Engineering Faculty
 * Licensed with Academic Free License version 2.1
 */
public class LoadGenerator 
{
	/**
	 * Time to wait until Load Units Synchronization (milliseconds)
	 * By default it is 5000 milliseconds but depends of the size and 
	 * weight of the charge, the number must be fixed. This Number indicates
	 * how much time the load units have to wait to execute. Only Applies when there is not
	 * a time gap between the execution (simultaneous execution) of each Load Unit. SYNC_GAP 
	 * is the time in milliseconds that the creation and preparation for the execution for all 
	 * the Load Units takes. 
	 */
	public static int SYNC_GAP = 5000;
	
	/**
	 * Load Generator Name
	 */
	private String name;
	
	/**
	 * Number of Executors 
	 */
	private int executorsNumber;
	
	/**
	 * Executors Service
	 */
	private ExecutorService executors;
	
	/**
	 * Load Units to Go
	 */
	private int loadUnits;
	
	/**
	 * Load Unit Seed 
	 */
	private Task unit;
	
	/**
	 * Gap Between Units milliseconds
	 */
	private long timeGap;
	
	/**
	 * Constructs new LoadGenerator with the alternative to specify the size of the ExecutorService 
	 * @param nameP
	 * @param executorsNumberP
	 * @param loadUnitsP
	 * @param unitP
	 * @param timeGapP
	 */
	public LoadGenerator(String nameP, int executorsNumberP,int loadUnitsP, Task unitP, long timeGapP)
	{
		name = nameP;
		executorsNumber = executorsNumberP;
		executors = Executors.newFixedThreadPool(executorsNumberP);
		loadUnits = loadUnitsP;
		unit = unitP;
		timeGap = timeGapP;
	
	}
	
	/**
	 * Constructs a new LoadGenerator by default with the same size for the ExecutorService 
	 * as the number of loadUnitsP
	 * @param nameP
	 * @param loadUnitsP
	 * @param unitP
	 * @param timeGapP
	 */
	public LoadGenerator(String nameP, int loadUnitsP, Task unitP, long timeGapP)
	{
		name = nameP;
		executorsNumber = loadUnitsP;
		executors = Executors.newFixedThreadPool(executorsNumber);
		loadUnits = loadUnitsP;
		unit = unitP;
		timeGap = timeGapP;
	}
	
	/**
	 * Generates the Load According the Configuration
	 */
	public void generate()
	{
		for(int i = 0; i<loadUnits; i++)
		{
			boolean sync = false;
			if(timeGap == 0)
			{
				sync = true;
			}
			LoadUnit unidad = new LoadUnit(unit, i, timeGap*i, sync);
			executors.execute(unidad);
			
			try 
			{
				Thread.sleep(timeGap);
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}		
	}
	
	/**
	 * Returns the SYNC_GAP Value
	 * @return the SYNC_GAP
	 */
	public static int getSYNC_GAP() 
	{
		return SYNC_GAP;
	}

	/**
	 * Sets the SYNC_GAP Value
	 * @param SYNCGAP the SYNC_GAP to set
	 */
	public static void setSYNC_GAP(int SYNC_GAP_P) 
	{
		SYNC_GAP = SYNC_GAP_P;
	}
	
	/**
	 * Main Test of the Application
	 * @param args
	 */
	public static void main(String [] args)
	{
//		Task t = new CargaTest();
//		LoadGenerator generator = new LoadGenerator("Test LoadGeneration", 50, 100000, t, 1000);
//		generator.generate();
	}
}
