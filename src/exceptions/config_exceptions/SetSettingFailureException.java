package exceptions.config_exceptions;

public class SetSettingFailureException extends ConfigurationException{
	public SetSettingFailureException() {
		super("Config File exception: Setting was not set");
	}
}
