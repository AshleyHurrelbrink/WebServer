package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import config.Config;
import config.Persist;
import exceptions.config_exceptions.ConfigurationException;
import exceptions.config_exceptions.InvalidMaintenanceDirectoryException;
import exceptions.config_exceptions.InvalidPortNumberException;
import exceptions.config_exceptions.InvalidRootDirectoryException;

public class PersistTest {
	
	String inexistentDir;
	String[] validRootDir;
	String[] invalidRootDir;
	String[] validMaintenanceDir;
	String invalidMaintenanceDir;
	int[] validPortNumbers = {10008, 10009};
	int[] invalidPortNumbers = {0, -1, 1022, 1};
	Config config;
	
	@Before
	public void init() throws IOException, ConfigurationException {
		
		
		this.inexistentDir = "..\\WebServer\\resources\\testing\\Persist\\InexistentDir";
		
		this.validRootDir =new String[2];
		this.validRootDir[0] = "..\\WebServer\\resources\\testing\\Persist\\ValidRootDirectory1";
		this.validRootDir[1] = "..\\WebServer\\resources\\testing\\Persist\\ValidRootDirectory2";
		
		this.invalidRootDir =new String[3];
		this.invalidRootDir[0]= "..\\WebServer\\resources\\testing\\Persist\\InvalidRootDirectory1";
		this.invalidRootDir[1]= "..\\WebServer\\resources\\testing\\Persist\\InvalidRootDirectory2";
		this.invalidRootDir[2]= "..\\WebServer\\resources\\testing\\Persist\\InvalidRootDirectory3";
		
		this.validMaintenanceDir =new String[2];
		this.validMaintenanceDir[0] = "..\\WebServer\\resources\\testing\\Persist\\ValidMaintenanceDirectory1";
		this.validMaintenanceDir[1] = "..\\WebServer\\resources\\testing\\Persist\\ValidMaintenanceDirectory2";
		
		this.invalidMaintenanceDir = "..\\WebServer\\resources\\testing\\Persist\\InvalidMaintenanceDirectory";
		
		//create configuration file
		String configPath = "..\\WebServer\\resources\\testing\\Persist\\testPersistConfig.properties";
		Config.createConfigFile(validMaintenanceDir[0], validRootDir[0], validPortNumbers[0], configPath);
		this.config = new Config (configPath);
	}
	
	//Set Port Number ---------------------------------------------------------------

	@Test(expected = InvalidPortNumberException.class)
	public void testSetIsInvalidPortNumber() throws IOException, ConfigurationException {
		Persist persist = new Persist(config);
		
		for(int port: invalidPortNumbers) {
			persist.setPortNumber(port);
		}
	}
	
	@Test
	public void testSetIsValidPortNumber() throws IOException, ConfigurationException {
		Persist persist = new Persist(config);
		
		for(int port: validPortNumbers) {
			persist.setPortNumber(port);
		}
	}

	@Test
	public void testSetPortNumber() throws ConfigurationException, IOException{
		Persist persist = new Persist(config);
		//current value is 10001
		persist.setPortNumber(10008);
		assertEquals(10008,persist.getPortNumber());
	}

	//Set Root Directory ---------------------------------------------------------------
	
	@Test
	public void testSetValidRootDirectory() throws ConfigurationException, IOException {
		Persist persist = new Persist(config);
		
		for(String dir : validRootDir) {
			persist.setRootDirectory(dir);
		}
	}
	
	@Test (expected = InvalidRootDirectoryException.class)
	public void testSetInvalidRootDirectory() throws ConfigurationException, IOException{
		Persist persist = new Persist(config);
		
		for(String dir : invalidRootDir) {
			persist.setRootDirectory(dir);
		}
	}
	
	@Test (expected = InvalidRootDirectoryException.class)
	public void testSetInexistentRootDirectory() throws ConfigurationException, IOException{
		Persist persist = new Persist(config);
		
		persist.setRootDirectory(inexistentDir);
	}
	
	//Set Maintenance Directory ---------------------------------------------------------------
	
	@Test
	public void testSetValidMaintenanceDirectory() throws ConfigurationException, IOException{
		Persist persist = new Persist(config);
		
		for(String dir : validMaintenanceDir) {
			persist.setMaintenanceDirectory(dir);
		}
	}
	
	@Test (expected = InvalidMaintenanceDirectoryException.class)
	public void testSetInvalidMaintenanceDirectory() throws ConfigurationException, IOException{
		Persist persist = new Persist(config);
		
		persist.setMaintenanceDirectory(invalidMaintenanceDir);
	}
	
	@Test (expected = InvalidMaintenanceDirectoryException.class)
	public void testSetInexistantMaintenanceDirectory() throws ConfigurationException, IOException{
		Persist persist = new Persist(config);
		
		persist.setMaintenanceDirectory(inexistentDir);
	}
	
	//Get Port Number ---------------------------------------------------------------
	
	@Test
	public void testGetPortNumber() throws ConfigurationException, IOException{
		Persist persist = new Persist(config);
		persist.getPortNumber();
	}
	
	//Get Root Directory ---------------------------------------------------------------
	
	@Test
	public void testGetRootDirectory() throws ConfigurationException, IOException{
		Persist persist = new Persist(config);
		persist.getRootDirectory();
	}
	
	//Get Maintenance Directory ---------------------------------------------------------------
	
	@Test
	public void testGetMaintenanceDirectory() throws ConfigurationException, IOException {
		Persist persist = new Persist(config);
		persist.getMaintenanceDirectory();
	}
	
	//Get and Set Root Directory ---------------------------------------------------------------
	
	@Test
	public void testSetAndGetRootDirectory() throws ConfigurationException, IOException{
		Persist persist = new Persist(config);
		//current value is validRootDir[0]
		persist.setRootDirectory(validRootDir[1]);
		assertEquals(validRootDir[1],persist.getRootDirectory());
	}
	
	//Get and Set Maintenance Directory ---------------------------------------------------------------
	
	@Test
	public void testSetAndGetMaintenanceDirectory() throws ConfigurationException, IOException{
		Persist persist = new Persist(config);
		//current value is validMaintenanceDir[0]
		persist.setMaintenanceDirectory(validMaintenanceDir[1]);
		assertEquals(validMaintenanceDir[1],persist.getMaintenanceDirectory());
	}
	
	

}
