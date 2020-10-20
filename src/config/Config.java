package config;

import exceptions.config_exceptions.LoadConfigurationFailureException;
import exceptions.config_exceptions.SaveConfigurationFailureException;

public class Config {
	
	String configurationFileName;
	
	public Config(String configurationFileName) {
		this.configurationFileName=configurationFileName;
	}
	
	public void loadConfiguration() throws LoadConfigurationFailureException {	
	}
	
	public void saveConfiguration() throws SaveConfigurationFailureException{	
	}
	
	public String getSetting(String key) {
		return null;
	}
	
	public boolean setSetting(String key, String name) {
		return false;
	}

}
