package exceptions;

public class LoadConfigurationFailureException extends Exception {

	public LoadConfigurationFailureException() {
		super("Invalid Config File");
	}
}
