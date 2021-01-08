package webserver;

import java.io.IOException;
import java.util.Scanner;

import config.Config;
import config.FileManager;
import config.Persist;
import exceptions.config_exceptions.ConfigurationException;
import exceptions.config_exceptions.LoadConfigurationFailureException;
import exceptions.webserver_exceptions.WebServerStateTransitionException;

public class Main {
	
	public static void main(String[] args) throws IOException, ConfigurationException, WebServerStateTransitionException {
		Persist persist = Main.getPersist();
		Main.startWebServer(persist);
	}

	public static Persist getPersist() throws IOException, LoadConfigurationFailureException {
		String configFilePath = "C:/Users/ahurr/Desktop/College/Year 4 (2020-2021)/sem1 (year 4)/SVV/WebServer/WebServer/WebserverTestingDirectories/config.properties";
		Config config = new Config(configFilePath);
		return new Persist(config);
	}

	public static void startWebServer(Persist persist){
		Scanner in= new Scanner(System.in);
		new Thread(() -> {
			Config config = null;
			try {
				WebServer.startWebServer(persist);
			} catch (ConfigurationException e) {
				e.printStackTrace();
			}
		}).start();

		new Thread(() -> {
			while(true) {
				TerminalGUI.printServerSettingsMenu();
				if(!WebServerState.isStopped()){
					try {
						System.out.println("Running at http://localhost:"+persist.getPortNumber());
					} catch (ConfigurationException e) {
						e.printStackTrace();
					}
				}
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
		}).start();
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
