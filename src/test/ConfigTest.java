package test;

import static org.junit.Assert.*;
import org.junit.Test;
import config.Config;
import exceptions.config_exceptions.LoadConfigurationFailureException;
import exceptions.config_exceptions.SaveConfigurationFailureException;

public class ConfigTest {

	@Test
	public void testSetSetting() {
		Config conf = new Config("valid_configuration_filename");
		assertTrue(conf.setSetting("PortNumber", "8000"));
		assertTrue(conf.setSetting("RootDirectory", "./www_root"));
		assertTrue(conf.setSetting("MaintenanceDirectory", "./www_maintenance"));
	}
	
	@Test
	public void testGetSetting() {
		Config conf = new Config("valid_configuration_filename");
		
		conf.setSetting("PortNumber", "8000");
		conf.setSetting("RootDirectory", "./www_root");
		conf.setSetting("MaintenanceDirectory", "./www_maintenance");

		assertEquals("8000",conf.getSetting("PortNumber"));
		assertEquals("./www_root", conf.getSetting("RootDirectory"));
		assertEquals("./www_maintenance", conf.getSetting("MaintenanceDirectory"));
	}
	
	@Test
	public void testValidLoadConfigurationFile() throws LoadConfigurationFailureException {
		Config conf = new Config("valid_configuration_filename");
		conf.loadConfiguration();
	}
	
	@Test (expected = LoadConfigurationFailureException.class)
	public void testInvalidLoadConfigurationFile() throws LoadConfigurationFailureException {
		Config conf = new Config("invalid_configuration_filename");
		conf.loadConfiguration();
	}
	
	@Test
	public void testValidSaveConfiguration() throws SaveConfigurationFailureException {
		Config conf = new Config("valid_configuration_filename");
		conf.saveConfiguration();
	}
	
	@Test (expected = SaveConfigurationFailureException.class)
	public void testInvalidSaveConfiguration() throws SaveConfigurationFailureException{
		Config conf = new Config("invalid_configuration_filename");
		conf.saveConfiguration();
	}
	
	

}
