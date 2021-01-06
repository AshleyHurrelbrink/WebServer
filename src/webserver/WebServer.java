package webserver;

import java.io.IOException;
import java.net.ServerSocket;

import config.Persist;
import exceptions.config_exceptions.ConfigurationException;
import exceptions.parsers_exceptions.InvalidRequestException;
import exceptions.webserver_exceptions.WebServerStateTransitionException;
import handlers.RequestHandler;
import handlers.ResponseHandler;

public class WebServer extends Thread{

	private ClientSocketManager clientSocketManager;
	private Persist persist;

	public WebServer(ClientSocketManager clientSocketManager, Persist persist) throws ConfigurationException {
		this.clientSocketManager = clientSocketManager;
		this.persist=persist;
		start();
	}
	
	public static void startWebServer(Persist persist) throws ConfigurationException {
		while(true) {
			if(WebServerState.isRunning()) {
				performOnMode(persist);
			}
			// do nothing for WebServerState.isStopped()
		}
	}
		
	public static void performOnMode(Persist persist) throws ConfigurationException {
		
		TerminalGUI.outputMessage("Starting. Enter into state: " + WebServerState.getCurrentState());
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(persist.getPortNumber());
			TerminalGUI.outputMessage("Connection Socket Created");
			
			try {
				while (!WebServerState.isStopped()) {
					TerminalGUI.outputMessage("Waiting for Connection");
					ClientSocketManager clientSocket = new ClientSocketManager(serverSocket.accept());
					new WebServer(clientSocket, persist);
				} 
			}catch (IOException e) {
				System.err.println("Accept failed.");
			}
			
			try {
				serverSocket.close();
			} catch (IOException e) {
				System.err.println("Could not close port: 10008.");
			}
			
		} catch (IOException e) {
			System.err.println("Could not listen on port: " + persist.getPortNumber());
		}
	}


	public void run(){
		TerminalGUI.outputMessage("New Communication Thread started");
		try {
			if(WebServerState.isRunning()) {
				runRunningMode();
			}

			if(WebServerState.isMaintenance()) {
				runMaintenanceMode();
			}

			this.clientSocketManager.closeAll();
		} catch (IOException e) {
			System.err.println("Problem with Communication Server");
		} catch (InvalidRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void runRunningMode() throws IOException, InvalidRequestException, ConfigurationException {
		Request request = RequestHandler.getRequest(this.clientSocketManager.getClientInput());
		Response response = ResponseHandler.getResponse(this.persist.getRootDirectory(), request);
		ResponseHandler.sendResponse(this.clientSocketManager.getClientSocket(), this.clientSocketManager.getClientOutput(), response);
	}

	public void runMaintenanceMode() throws IOException, ConfigurationException, InvalidRequestException {
		Request request = RequestHandler.getRequest(this.clientSocketManager.getClientInput());
		Response response = ResponseHandler.getResponse(this.persist.getMaintenanceDirectory(),request);
		ResponseHandler.sendResponse(this.clientSocketManager.getClientSocket(), this.clientSocketManager.getClientOutput(), response);
	}

}

