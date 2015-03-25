package vafus.plugins.vissim.interfaces;

import java.io.File;

import vafus.plugins.vissim.exceptions.CommunicationException;

public interface ISimulation {
	public void loadSimulation(File shapeFile);
	public void endSimulation() throws CommunicationException;
}
