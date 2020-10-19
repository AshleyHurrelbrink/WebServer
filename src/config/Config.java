package config;

import exceptions.LoadConfigurationFailureException;
import exceptions.SaveConfigurationFailureException;

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
