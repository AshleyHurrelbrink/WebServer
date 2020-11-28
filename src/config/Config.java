package config;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import exceptions.config_exceptions.GetSettingFailureException;
import exceptions.config_exceptions.LoadConfigurationFailureException;
import exceptions.config_exceptions.SetSettingFailureException;
import validators.ConfigValidator;

public class Config {
	
	private String configurationFileName;
	private FileReader reader;
	private Properties prop;
	
	public Config(String configurationFileName) throws  IOException, LoadConfigurationFailureException {
		this.configurationFileName=configurationFileName;
		loadConfiguration();
	}
	
	public void loadConfiguration() throws LoadConfigurationFailureException, IOException {	
		prop = new Properties();
		try {
			this.reader = new FileReader(configurationFileName);
	
			if (reader != null) {
				prop.load(reader);
			} else {
				throw new LoadConfigurationFailureException("Input Configuration file '" + configurationFileName + "' not found in the classpath");
			}
		} catch (IOException ex) {
            throw new IOException("Error on loading Config file");
        }
	}
	
	
	public void saveConfiguration() throws IOException{
		try {
			FileWriter output = new FileWriter(this.configurationFileName);
			prop.store(output, "Changed file");
        } catch (IOException ex) {
            throw new IOException("Save Configuration: Error on saveing Config file");
        }
	}
	
	public String getSetting(String key) throws GetSettingFailureException{
		String value = prop.getProperty(key);
		
		if(!ConfigValidator.validateGetSetting(value)) {
			throw new GetSettingFailureException();
		}
		return value;
	}
	
	public void setSetting(String key, String value) throws SetSettingFailureException {
		prop.setProperty(key, value);
		
		if(!ConfigValidator.validateSetSetting(value,prop.getProperty(key))) {
			throw new SetSettingFailureException();
		}
	}
	
	public void printConfig() {
		System.out.println("PrintConfig -------------------------------");
		prop.forEach((k, v) -> System.out.println("Key : " + k + ", Value : " + v));
		System.out.println("-------------------------------------------");
	}

}
