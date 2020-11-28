package test;

import static org.junit.Assert.*;

import org.junit.Test;

import config.Config;
import config.Persist;
import exceptions.config_exceptions.InvalidMaintenanceDirectoryException;
import exceptions.config_exceptions.InvalidPortNumberException;
import exceptions.config_exceptions.InvalidRootDirectoryException;

public class PersistTest {
/*
	@Test(expected = InvalidPortNumberException.class)
	public void testInvalidSetPortNumber() throws InvalidPortNumberException {
		Persist persist = new Persist();
		int portNumbers[]= {0, -1, 1022, 1};
		
		for(int nr: portNumbers) {
			persist.setPortNumber(nr);
		}
	}
	
	@Test
	public void testValidSetPortNumber() throws InvalidPortNumberException {
		Persist persist = new Persist();
		int portNumbers[]= {10008, 10009};
		
		for(int nr: portNumbers) {
			persist.setPortNumber(nr);
		}
	}

	
	@Test(expected = InvalidRootDirectoryException.class)
	public void testInvalidRootDirectory() throws InvalidRootDirectoryException {
		Persist persist = new Persist();
		String rootDirectories[]= {"C:\\File.txt","File.txt", "Folder\\File.txt"};
		
		for(String dir: rootDirectories) {
			persist.setRootDirectory(dir);
		}
	}
	
	@Test
	public void testValidRootDirectory() throws InvalidRootDirectoryException {
		Persist persist = new Persist();
		String rootDirectories[]= {"C:\\RootFolder","RootFolder", "Folder\\RootFolder"};
		
		for(String dir: rootDirectories) {
			persist.setRootDirectory(dir);
		}
	}
	
	@Test(expected = InvalidMaintenanceDirectoryException.class)
	public void testInvalidMaintenanceDirectory() throws InvalidMaintenanceDirectoryException {
		Persist persist = new Persist();
		String maintenanceDirectories[]= {"C:\\MaintenanceFolder","MaintenanceFolder", "Folder\\MaintenanceFolder"};
		
		for(String dir: maintenanceDirectories) {
			persist.setMaintenanceDirectory(dir);
		}
	}
	
	@Test
	public void testValidMaintenanceDirectory() throws InvalidMaintenanceDirectoryException {
		Persist persist = new Persist();
		String maintenanceDirectories[]= {"C:\\File.txt","File.txt", "Folder\\File.txt"};
		
		for(String dir: maintenanceDirectories) {
			persist.setMaintenanceDirectory(dir);
		}
	}
	
	@Test
	public void testGetPortNumber() throws InvalidPortNumberException{
		Persist persist = new Persist();
		persist.setPortNumber(10008);
		assertEquals(10008,persist.getPortNumber());
	}
	
	@Test
	public void testGetRootDirectory() throws InvalidRootDirectoryException{
		Persist persist = new Persist();
		persist.setRootDirectory("./wwww_root");
		assertEquals("./www_root",persist.getRootDirectory());
	}
	
	@Test
	public void testGetMaintenanceDirectory() throws InvalidMaintenanceDirectoryException{
		Persist persist = new Persist();
		persist.setMaintenanceDirectory("./www_maintenance");
		assertEquals("./www_maintenance",persist.getMaintenanceDirectory());
	}
*/
}
