package exceptions.config_exceptions;

public class InvalidMaintenanceDirectoryException extends ConfigurationException {
	
	public InvalidMaintenanceDirectoryException() {
		super("Invalid Maintenance Directory");
	}
}
