package exceptions.config_exceptions;

public class InvalidPortNumberException extends ConfigurationException {
	
	public InvalidPortNumberException() {
		super("Invalid Port Number");
	}
}
