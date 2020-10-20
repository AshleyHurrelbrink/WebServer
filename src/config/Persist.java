package config;

import exceptions.config_exceptions.InvalidMaintenanceDirectoryException;
import exceptions.config_exceptions.InvalidPortNumberException;
import exceptions.config_exceptions.InvalidRootDirectoryException;
import validators.MaintenanceDirectoryValidator;
import validators.PortNumberValidator;
import validators.RootDirectoryValidator;

public class Persist {
		
	private String rootDirectory = "c:\\www-root\\mySite";
	private String maintenanceDirectory = "c:\\www-root\\maintenance";
	private int portNumber = 10008;
			
	//Web server info
	private String address;
	private String listeningPort;
	//Web server configuration
	static final String default_page[] = {"index.html", "index.htm", "default.html"};
	static final String page_not_found = "404.html";
		
	
	public Persist() {	
	}
	
	public String getRootDirectory() {
		return null;
	}
	
	public String getMaintenanceDirectory() {
		return null;
	}
	
	public int getPortNumber() {
		return 0;
	}
	
	public void setRootDirectory(String rootDirectory) throws InvalidRootDirectoryException {
		if(!RootDirectoryValidator.validate(rootDirectory))
		{
			throw new InvalidRootDirectoryException();
		}
		this.rootDirectory=rootDirectory;
	}
	
	public void setMaintenanceDirectory(String maintenanceDirectory) throws InvalidMaintenanceDirectoryException {
		if(!MaintenanceDirectoryValidator.validate(maintenanceDirectory)) {
			throw new InvalidMaintenanceDirectoryException();
		}
		this.maintenanceDirectory=maintenanceDirectory;
	}
	
	public void setPortNumber(int portNumber) throws InvalidPortNumberException {
		if (!PortNumberValidator.validate(portNumber)) {
			throw new InvalidPortNumberException();
		}
		this.portNumber=portNumber;
	}
}
