package webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URISyntaxException;

public class Main {
	
		 
	public static void main(String[] args) throws IOException, URISyntaxException {
			ServerSocket serverSocket =null;
			
	        try {
	        	serverSocket = new ServerSocket(10008);
				System.out.println("Connection Socket Created");
				try {
					while (true) {
						System.out.println("Waiting for Connection");
						WebServer web= new WebServer(serverSocket.accept());
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
	 
	/*
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		int port = 10008;

		try {
			serverSocket = new ServerSocket(port);
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
	}*/
}
