package vafus.plugins.vissim.communication;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import vafus.plugins.vissim.JVafus;
import vafus.plugins.vissim.anm_generator.ANMgenerator;
import vafus.plugins.vissim.communication.filetransfer.HttpFileTransferService;
import vafus.plugins.vissim.exceptions.CommunicationException;
import vafus.plugins.vissim.interfaces.ISimulation;

public class Simulation extends Thread implements ISimulation {

	// ------------------------
	// Attributes
	// ------------------------
	private JVafus core;
	private HttpFileTransferService fts;
	private String logId;
	
	private String hostname;
	private int port;
	
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	
	// ------------------------
	// Constructor
	// ------------------------
	public Simulation(JVafus core, HttpFileTransferService fts, String hostname, String port) throws CommunicationException {
		this.core = core;
		this.fts = fts;
		this.logId = "[Simulation ("+hostname+":"+port+")]";
		
		this.hostname = hostname;
		this.port = Integer.parseInt(port);
		
		try {
			this.socket = new Socket(this.hostname, this.port);
			this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			this.out = new PrintWriter(this.socket.getOutputStream(), true);
			
			writeMessage(Protocol.CONNECT);
			String response = in.readLine(); 
			if (response.equals(Protocol.OK)) {
				System.out.println(logId+": pyVissim is ready to handle this connection...");
				this.start();
			}
			else throw new CommunicationException(logId+": I can not connect with the server, the server replies "+response);
		} catch (IOException e) {
			throw new CommunicationException(logId+": I can not connect with the server: "+e.getMessage(),e.getStackTrace());
		}
		
	}
	
	// ------------------------
	// Methods
	// ------------------------
	public void run() {
		// Una vez establecida la conexión entro en el ciclo de escucha...
		while(!Thread.currentThread().isInterrupted()) {
			try {
				String message =in.readLine();
				if (message != null) readMessage(message);
				else break;
			} catch (IOException e) {
				if (!this.isInterrupted()) {
					System.err.println(logId+" We had a communication problem!! ");
					e.printStackTrace();
				}
				break;
			}
		}
		if (!this.socket.isClosed()) disconnect();
	}

	private void readMessage(String incomingMessage) {
		System.out.println(logId+"<"+incomingMessage+"> --> IN");
		if (incomingMessage.equals(Protocol.DISCONNECT)) {
			disconnect();
		}
	}
	
	private void writeMessage(String outgoingMessage) {
		System.out.println(logId+"OUT --> <"+outgoingMessage+">");
		this.out.println(outgoingMessage);
	}
	
	
	private void disconnect() {
		this.interrupt();
		try {
			if (!this.socket.isInputShutdown())	this.socket.shutdownInput();
			if (!this.socket.isOutputShutdown()) this.socket.shutdownOutput();
			if (!this.socket.isClosed()) this.socket.close();
			System.out.println(logId+": Socket closed.");
			this.core.removeSocket(this);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	// ------------------------
	// Interface Methods 
	// ------------------------
	@Override
	public void endSimulation() {
		writeMessage(Protocol.DISCONNECT);
		disconnect();
	}

	@Override
	public void loadSimulation(File shapeFile) {
		// First we are going to make the simulation file available in the HTTP file transfer server...
		
		
		
	}

}
