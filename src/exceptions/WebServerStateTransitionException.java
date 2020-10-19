package exceptions;

public class WebServerStateTransitionException extends Exception {
	
	public WebServerStateTransitionException() {
		super("State transition failed");
	}
}
