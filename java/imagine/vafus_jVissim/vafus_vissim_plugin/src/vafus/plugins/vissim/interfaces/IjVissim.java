package vafus.plugins.vissim.interfaces;

import vafus.plugins.vissim.exceptions.CommunicationException;

public interface IjVissim {
	
	public ISimulation getSimulation(String hostname, String port) throws CommunicationException;

	public void shutDown();
	
}
