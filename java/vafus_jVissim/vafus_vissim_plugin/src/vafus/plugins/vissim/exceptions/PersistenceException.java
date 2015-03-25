package vafus.plugins.vissim.exceptions;

@SuppressWarnings("serial")
public class PersistenceException extends JVissimException {

	public PersistenceException(String messsage,StackTraceElement[] stackTrace) {
		super(messsage);
		super.setStackTrace(stackTrace);
	}
	
	public PersistenceException(String messsage) {
		super(messsage);
	}
}
