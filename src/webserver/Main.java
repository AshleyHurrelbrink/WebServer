package webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import config.Config;
import config.FileManager;
import config.Persist;
import exceptions.config_exceptions.ConfigurationException;
import exceptions.config_exceptions.GetSettingFailureException;
import exceptions.config_exceptions.InvalidMaintenanceDirectoryException;
import exceptions.config_exceptions.LoadConfigurationFailureException;
import exceptions.config_exceptions.SetSettingFailureException;
import exceptions.webserver_exceptions.WebServerStateTransitionException;

public class Main {

	
	public static void main(String[] args) throws IOException, ConfigurationException, WebServerStateTransitionException {
		
		String configFilePath = "C:/Users/ahurr/Desktop/College/Year 4 (2020-2021)/sem1 (year 4)/SVV/WebServer/WebServer/WebserverTestingDirectories/config.properties";
		
		WebServer webServer= new WebServer(configFilePath);
		webServer.startWebServer();

		
		/*new Thread(new Runnable() {
	        @Override
	        public void run() {
	            try {
	            	WebServer webServer= new WebServer(configFilePath);
	        		webServer.startWebServer();
	            } catch (IOException e) {
	                e.printStackTrace();
	            } catch (ConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
	            } catch (WebServerStateTransitionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	     }).start();
	        
		
		 /*new Thread(new Runnable() {
		        @Override
		      public void run() {
		           Scanner in = new Scanner(System.in);

		           while (true) {
		                printServerSettingsMenu();

		                try {
		                    switch (in.nextInt()) {
		                        case 0:
		                            WebServerState.setStopped();
		                            break;
		                        case 1:
		                            WebServerState.setRunning();
		                            break;
		                        case 2:
		                        	 WebServerState.setMaintenance();
		                            break;
		                        case 3:
		                            System.exit(0);
		                    }

		                } catch (WebServerStateTransitionException e) {
		                    e.printStackTrace();
		                }
		            }
		        }
		    }).start();	*/	
	        
	}

	public static void printServerSettingsMenu(){
	    System.out.println("***************Server settings***************");
	    System.out.println("Current state: " + WebServerState.getCurrentState());
	    System.out.println("* Set to state 0 (Stopped)");
	    System.out.println("* Set to state 1 (Running)");
	    System.out.println("* Set to state 2 (Maintenance)");
	    System.out.println("* Exit program: x");
	    System.out.println("Enter your option:");
	    //System.out.flush();
	}
	
	public void testConfig() throws LoadConfigurationFailureException, IOException{
		Config properties = new Config("resources/config/config.properties");
		properties.loadConfiguration();
		properties.printConfig();
	}
	
	public void testPersist() throws IOException, ConfigurationException{
		String content = "";
		
		String filePath = "..\\WebServer\\resources\\testing\\Persist\\testPersistconfig.properties";
		FileManager.writeContentToFile(filePath, content);
		Config config = new Config (filePath);
		config.printConfig();
	}
}
