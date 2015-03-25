package vafus.plugins.vissim;

import java.util.ArrayList;

import vafus.plugins.vissim.communication.Simulation;
import vafus.plugins.vissim.communication.filetransfer.HttpFileTransferService;
import vafus.plugins.vissim.exceptions.CommunicationException;
import vafus.plugins.vissim.exceptions.JVissimException;
import vafus.plugins.vissim.interfaces.ISimulation;
import vafus.plugins.vissim.interfaces.IVafus;
import vafus.plugins.vissim.interfaces.IjVissim;

public class JVafus implements IjVissim{
	// ------------------------
	// Constants
	// ------------------------
	private final static int HTTP_PORT = 3005;
	private final static String HTTP_ROOT = "./data/http_root";
	
	// ------------------------
	// Attributes
	// ------------------------
	private IVafus vafus;
	private HttpFileTransferService httpFileTransferServer;
	private ArrayList<Simulation> simulations;

	// ------------------------
	// Constructor
	// ------------------------
	public JVafus(IVafus vafus) throws JVissimException {
		this.vafus = vafus;
		this.simulations = new ArrayList<Simulation>();
		
		this.httpFileTransferServer = new HttpFileTransferService(HTTP_PORT,HTTP_ROOT);
	}

	// ------------------------
	// Interface Methods
	// ------------------------
	@Override
	public ISimulation getSimulation(String hostname, String port) throws CommunicationException {
		Simulation cs = new Simulation(this, httpFileTransferServer, hostname, port);
		this.simulations.add(cs);
		return cs;
	}

	@Override
	public void shutDown() {
		this.httpFileTransferServer.shutDown();
		
		if (this.simulations.size() != 0) {
			System.out.println("[Core] There are "+this.simulations.size()+" active simulations!");
			@SuppressWarnings("unchecked")
			ArrayList<Simulation> tempSockets = (ArrayList<Simulation>) this.simulations.clone();
			for (Simulation cs : tempSockets) cs.endSimulation();
		}
	}

	// ------------------------
	// Auxiliary Methods
	// ------------------------
	public void removeSocket(Simulation clientSocket) {
		this.simulations.remove(clientSocket);
	}
}
