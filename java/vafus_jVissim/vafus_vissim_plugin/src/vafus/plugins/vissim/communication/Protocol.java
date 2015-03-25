package vafus.plugins.vissim.communication;

public class Protocol {
	// ------------------------
	// Protocol
	// ------------------------
	public final static String _SEPARATOR = "::"; // If the command requires additional information, it should come in the same message
	
	// OPERATIVE COMMANDS:
	public final static String CONNECT = "CONNECT";
	public final static String DISCONNECT = "DISCONNECT";
	public final static String OK = "OK";
	public final static String RETRY = "RETRY";
	
	// SIMULATION RELATED COMMANDS:
	public final static String LOAD_SIMULATION = "LS";

	// ------------------------
	// Attributes
	// ------------------------
	public String command;
	public Object data;
	
	// ------------------------
	// Constructor
	// ------------------------
	public Protocol(String command, Object data) {
		this.command = command;
		this.data = data;
	}
}
