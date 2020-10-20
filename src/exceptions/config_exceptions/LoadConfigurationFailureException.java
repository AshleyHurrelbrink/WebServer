package exceptions.config_exceptions;

public class LoadConfigurationFailureException extends Exception {

	public LoadConfigurationFailureException() {
		super("Invalid Config File");
	}
}
