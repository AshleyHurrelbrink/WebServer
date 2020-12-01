package webserver;

import java.io.IOException;
import java.net.ServerSocket;

import config.Config;
import config.Persist;
import exceptions.config_exceptions.ConfigurationException;
import exceptions.config_exceptions.LoadConfigurationFailureException;
import exceptions.webserver_exceptions.WebServerStateTransitionException;

public class WebServer {
	
	private Persist persist;
	
	public WebServer(String configFilePath) throws LoadConfigurationFailureException, IOException {
		this.persist = new Persist(new Config(configFilePath));
	}
	
	public void startWebServer() throws ConfigurationException, IOException, WebServerStateTransitionException {
		
		WebServerState.setRunning();
		
		while(true) {
			if(WebServerState.isRunning()|| WebServerState.isMaintenance()) {
				performRunningOrMaintenanceMode();
			}
					
			if(WebServerState.isStopped()) {
				performStoppedMode();
			}		
		}
	}
		
	public void performRunningOrMaintenanceMode() throws ConfigurationException {
		
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket(this.persist.getPortNumber());
			System.out.println("Connection Socket Created");
			try {
				while (WebServerState.isRunning()) {
					System.out.println("Waiting for Connection");
					ClientSocketManager clientSocket = new ClientSocketManager(serverSocket.accept());
					new WebServerThread(clientSocket, this.persist);
				} 
			}catch (IOException e) {
				System.err.println("Accept failed.");
				System.exit(1);
			}
		} catch (IOException e) {
			System.err.println("Could not listen on port: " + this.persist.getPortNumber());
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

	
	public void performStoppedMode() throws WebServerStateTransitionException {
		System.out.println("Stopped Mode was Activated -------------------------");
		
		while (WebServerState.isStopped()) {
			System.out.println(WebServerState.getCurrentState());
		   WebServerState.setRunning();
		   System.out.println("AFTER:" + WebServerState.getCurrentState());
		}
	}
}

