package webserver;

import java.net.*;

import config.Persist;
import handlers.ResponseHandler;

import java.io.*;

public class WebServerThread extends Thread {
	
	private ClientSocketManager clientSocketManager;
	private Persist persist;

	public WebServerThread(ClientSocketManager clientSocketManager, Persist persist) {
		this.clientSocketManager = clientSocketManager;
		this.persist=persist;
		start();
	}

	public void run() {
		System.out.println("New Communication Thread Started");

		try {
			if(WebServerState.isRunning()) {
				performRunningMode();
			}
					
			if(WebServerState.isMaintenance()) {
				performMaintenanceMode();
			}
					
			if(WebServerState.isStopped()) {
				performStoppedMode();
			}	
	

			this.clientSocketManager.closeAll();
		} catch (IOException e) {
			System.err.println("Problem with Communication Server");
			System.exit(1);
		}
	}
	
	public void performStoppedMode() {
		
	}
	
	public void performRunningMode() throws IOException {
		String request=WebServerThread.getRequest(this.clientSocketManager.getClientInput());
		ResponseHandler.sendResponse(this.clientSocketManager.getClientSocket(),this.clientSocketManager.getClientOutput(),"HTTP/1.1","OK", "text/html", "<html><p><b>hei</b> there</p></html>".getBytes());	
	}
	
	public void performMaintenanceMode() {
		
	}
	
	public static String getRequest(BufferedReader in) throws IOException {
		String inputLine;
		StringBuilder request = new StringBuilder();
		
		while ((inputLine = in.readLine()) != null) {
			System.out.println(inputLine);
			request.append(inputLine);
			
			if (inputLine.trim().equals(""))
				break;
		}
		return request.toString();
	}
		
}