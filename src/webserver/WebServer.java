package webserver;

import java.net.*;
import java.io.*;

public class WebServer extends Thread {
	
	protected Socket clientSocket;

	public WebServer(Socket clientSoc) {
		clientSocket = clientSoc;
		start();
	}

	public void run() {
		System.out.println("New Communication Thread Started");

		try {
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),
					true);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));

			String request=WebServerManager.getRequest(in);
			
			//request handler
			WebServerManager.sendResponse(clientSocket,"OK", "text/html", "<html><p><b>hei</b> there</p></html>".getBytes());
			
			/*while ((inputLine = in.readLine()) != null) {
				System.out.println("From Client: " + inputLine);
				out.println("From Server:" + inputLine);

				if (inputLine.trim().equals(""))
					break;
			}*/

			out.close();
			in.close();
			clientSocket.close();
		} catch (IOException e) {
			System.err.println("Problem with Communication Server");
			System.exit(1);
		}
	}
}