package test;

import static org.junit.Assert.*;
import java.net.ServerSocket;
import org.junit.Test;
import webserver.WebServer;
import java.io.*;

public class WebServerTest extends Thread{
	
	WebServer server;
	ServerSocket serverSocket = null;

	@Test
	public void setUp(){
		boolean notThrown=true;
		try {
			serverSocket = new ServerSocket(10008);
			server= new WebServer(serverSocket.accept());
		} catch (IOException e) {
			notThrown=false;
		}
		assertFalse(notThrown);
	}
	
	@Test
	public void testIsStoppedTheInitialState(){
		boolean notThrown=true;
		try {
			serverSocket = new ServerSocket(10008);
			server= new WebServer(serverSocket.accept());
			assertFalse(server.getStatus().isStopped());
		} catch (IOException e) {
			notThrown=false;
		}
		assertFalse(notThrown);	
	}
	
	@Test 
	public void testRequestWhileInStoppedState(){
		//connection timeout
	}
	
	@Test 
	public void testPageFoundWhileInRunningModeState(){
		//server responds to request with a page
	}
	
	@Test 
	public void testPageNotFoundWhileInRunningModeState(){
		//server responds to request with 404
	}
	
	@Test 
	public void testDefaultPageWhileInRunningModeState(){
		//if no page is specified it will go to default page if found
	}
	
	@Test
	public void testMaintenanceState() {
		//respond to all requests with a specified page
	}
	
	@Test
	public void testIsMaintenanceState() {
		//tests if in maintenance state
		boolean notThrown=true;
		try {
			serverSocket = new ServerSocket(10008);
			server= new WebServer(serverSocket.accept());
			server.setMaintenanceState();
			assertFalse(server.getStatus().isMaintenance());
		} catch (IOException e) {
			notThrown=false;
		}
		assertFalse(notThrown);
	}
	
	@Test
	public void testIsRunningState() {
		//tests if in running state
		boolean notThrown=true;
		try {
			serverSocket = new ServerSocket(10008);
			server= new WebServer(serverSocket.accept());
			server.setRunningState();
			assertFalse(server.getStatus().isRunning());
		} catch (IOException e) {
			notThrown=false;
		}
		assertFalse(notThrown);
	}
	
	@Test
	public void testIsStoppedState() {
		//tests if in stopped state
		boolean notThrown=true;
		try {
			serverSocket = new ServerSocket(10008);
			server= new WebServer(serverSocket.accept());
			server.setStoppedState();
			assertFalse(server.getStatus().isStopped());
		} catch (IOException e) {
			notThrown=false;
		}
		assertFalse(notThrown);
	}


}
