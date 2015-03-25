package vafus.plugins.vissim.exceptions;

@SuppressWarnings("serial")
public class JVissimException extends Exception {

	public JVissimException(String messsage,StackTraceElement[] stackTrace) {
		super(messsage);
		super.setStackTrace(stackTrace);
	}
	
	public JVissimException(String messsage) {
		super(messsage);
	}
}
