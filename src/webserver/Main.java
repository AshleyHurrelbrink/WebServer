package webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import config.Config;
import config.Persist;
import exceptions.config_exceptions.ConfigurationException;
import exceptions.config_exceptions.GetSettingFailureException;
import exceptions.config_exceptions.InvalidMaintenanceDirectoryException;
import exceptions.config_exceptions.LoadConfigurationFailureException;
import exceptions.config_exceptions.SetSettingFailureException;

public class Main {

	public static void main(String[] args) throws IOException, ConfigurationException {
		Main main = new Main();
		main.testPersist();
	}
	
	public void testConfig() throws LoadConfigurationFailureException, IOException{
		Config properties = new Config("resources/config/config.properties");
		properties.loadConfiguration();
		properties.printConfig();
	}
	
	public void testPersist() throws IOException, ConfigurationException{
		Config config = new Config("resources/config/config.properties");
		Persist persist = new Persist(config);
		
		System.out.println("before: "+persist.getMaintenanceDirectory());
		persist.setMaintenanceDirectory("c:\\www-root\\maintenance2");
		System.out.println("after: "+persist.getMaintenanceDirectory());
	}
	
	public void startWebServer() {
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(10008);
			System.out.println("Connection Socket Created");
			try {
				while (true) {
					System.out.println("Waiting for Connection");
					new WebServer(serverSocket.accept());
				}
			} catch (IOException e) {
				System.err.println("Accept failed.");
				System.exit(1);
			}
		} catch (IOException e) {
			System.err.println("Could not listen on port: 10008.");
			System.exit(1);
		} finally {
			try {
				serverSocket.close();
			} catch (IOException e) {
				System.err.println("Could not close port: 10008.");
				System.exit(1);
			}
		}
	}
}
