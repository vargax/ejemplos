package vafus.plugins.vissim.exceptions;

@SuppressWarnings("serial")
public class CommunicationException extends JVissimException {

	public CommunicationException(String messsage,StackTraceElement[] stackTrace) {
		super(messsage);
		super.setStackTrace(stackTrace);
	}
	
	public CommunicationException(String messsage) {
		super(messsage);
	}
}
