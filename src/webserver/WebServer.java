package webserver;

import java.net.*;
import java.io.*;

public class WebServer extends Thread {

	protected Socket clientSocket;
	
	//Web server configuration
	static final String default_page[] = {"index.html", "index.htm", "default.html"};
	static final String page_not_found = "404.html";
	private String webRootDirectory = "c:\\www-root\\mySite";
	private String maintenanceDirectory = "c:\\www-root\\maintenance";
	private int port = 10008;
	
	//Web server info
	private WebServerStatus status=new WebServerStatus();
	private String address;
	private String listeningPort;
	
	public WebServer(Socket clientSoc) {
		clientSocket = clientSoc;
		start();
	}

	public void run() {
		System.out.println("New Communication Thread Started");

		try {
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			String inputLine;
			
			while ((inputLine = in.readLine()) != null) {
				System.out.println("From Client: " + inputLine);
				out.println("From Server:" + inputLine);

				if (inputLine.trim().equals("")) 
					break;
			}

			out.close();
			in.close();
			clientSocket.close();
		} catch (IOException e) {
			System.err.println("Problem with Communication Server");
			System.exit(1);
		}
	}
	
	
	public String request() throws IOException {
		String requestedResource = "";
		/*BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		 
        String requestedResource = "";
        String incomingLineFromClient;
        while ((incomingLineFromClient = in.readLine()) != null) {
            System.out.println(incomingLineFromClient);
 
            if(incomingLineFromClient.contains("HTTP/1.1")) {
                requestedResource = incomingLineFromClient;
            }
 
            if (incomingLineFromClient.equals(""))
                break;
        }
        response(requestedResource);*/
		return requestedResource;
	}
	
	public String response(String requestedResource) {
		String response = "You have requested this resource: " + requestedResource;
		/*PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
		 
        String response = "You have requested this resource: " + requestedResource;
 
        out.print("HTTP/1.1 200 OK\n");
        out.print("Content-Length: " + response.length() + "\n");
        out.print("Content-Type: text/html; charset=utf-8\n");
        out.print("Date: Tue, 25 Oct 2016 08:17:59 GMT\n");
        out.print("\n");
        out.print(response);
        out.flush();*/
		return response;
	}
	
	
	public void stopServer() {
		// se pot configura toate campurile interfetei;
		// serverul poate porni doar în modul normal: running;
		// informatiile despre server vor indica starea initiala: not running;
	
		status.setStopped();
		address="not running";
		listeningPort="not running";
		
	}
	
	public void runServer() {
		// interfata va oferi posibilitatea opririi serverului;
		// se poate configura doar directorul de mentenanta;
		// si este activ modul de trecere a serverului in starea de mentenanta;
		// se vor afisa informatii valide despre starea serverului;
		
		status.setRunning();
	}
	
	public void maintenanceServer() {
		// interfata va oferi posibilitatea opririi serverului;
		// si trecerii acestuia în modul normal de functionare;
		// se poate configura doar directorul radacina al site-ului;
		// se vor afisa informatii valide despre starea serverului;
		
		status.setMaintenance();
	}
	
	//webroot directory
	public String getWebRootDirectory() {
		return webRootDirectory;
	}
	
	public void setWebRootDirectory(String directory_path) {
		webRootDirectory=directory_path;
	}
	
	public boolean isValidWebRootDirectory(String directory_path) {
		//conditions
		return true;
	}
	
	//maintenance directory
	public String getMaintenanceDirectory() {
		return maintenanceDirectory;
	}
	
	public void setMaintenanceDirectory(String directory_path) {
		maintenanceDirectory=directory_path;;
	}
	
	public boolean isValidMaintenanceDirectory(String directory_path) {
		//html file
		return true;
	}
	
	public WebServerStatus getStatus() {
		return status;
	}
		
}