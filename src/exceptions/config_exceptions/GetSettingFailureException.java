package exceptions.config_exceptions;

public class GetSettingFailureException extends ConfigurationException{
	public GetSettingFailureException() {
		super("Config File exception: Setting was not found");
	}
}
