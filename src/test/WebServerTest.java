package test;

import static org.junit.Assert.*;
import java.net.ServerSocket;
import org.junit.Test;

import webserver.Request;
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
	
	//@Test
	/*public void testPortOccupied(){
		boolean thrown=false;
		try {
			ServerSocket serverSocket2 = new ServerSocket(10008);
		} catch (IOException e) {
			thrown=true;
		}
		assertTrue(thrown);
	}*/
	
	@Test
	public void testIsStoppedWhenInInitialState(){
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
	public void testSetMaintenanceStateFromRunningState() {
		//tests set maintenance state
		//maintenance state can only be set if running

		server.stopServer();;
		server.runServer();
		server.maintenanceServer();
		assertFalse(server.getStatus().isMaintenance());
	}
	
	@Test
	public void testNotSetMaintenanceStateFromStoppedState() {
		//tests set maintenance state
		//maintenance state can only be set if running
		server.stopServer();
		server.maintenanceServer();
		assertTrue(server.getStatus().isMaintenance());
	}
	
	@Test
	public void testSetRunningStateFromStoppedState() {
		//tests set in running state
		server.stopServer();
		server.runServer();
		assertFalse(server.getStatus().isRunning());
	}
	
	@Test
	public void testSetRunningStateFromMaintenanceState() {
		//tests set in running state
		server.stopServer();
		server.runServer();
		server.maintenanceServer();
		server.runServer();
		assertFalse(server.getStatus().isRunning());
	}
	
	@Test
	public void testSetStoppedStateFromRunningState() {
		//tests set in stopped state
		server.stopServer();
		server.runServer();
		server.stopServer();
		assertFalse(server.getStatus().isStopped());
	}

	@Test
	public void testSetStoppedStateFromMaintenanceState() {
		//tests set in stopped state
		server.stopServer();
		server.maintenanceServer();
		server.stopServer();
		assertFalse(server.getStatus().isStopped());
	}
	

	@Test 
	public void testPageFoundResponse(){
		//server responds to request with a page
		String requestedResource="GET / HTTP/1.1\r\n"
				+ "Host: localhost:10008\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:81.0) Gecko/20100101 Firefox/81.0\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n"
				+ "Accept-Language: en-US,en;q=0.5\r\n"
				+ "Accept-Encoding: gzip, deflate\r\n"
				+ "Connection: keep-alive\r\n"
				+ "Upgrade-Insecure-Requests: 1\r\n"
				+ "Cache-Control: max-age=0";
		String expectedResponse="";
		String response=server.response(requestedResource);
		assertEquals(response, expectedResponse);
	}
	
	@Test 
	public void testPageNotFoundResponse(){
		//server responds to request with 404
		String requestedResource="GET / HTTP/1.1\r\n"
				+ "Host: localhost:10008\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:81.0) Gecko/20100101 Firefox/81.0\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n"
				+ "Accept-Language: en-US,en;q=0.5\r\n"
				+ "Accept-Encoding: gzip, deflate\r\n"
				+ "Connection: keep-alive\r\n"
				+ "Upgrade-Insecure-Requests: 1\r\n"
				+ "Cache-Control: max-age=0";
		String expectedResponse="";
		String response=server.response(requestedResource);
		assertEquals(response, expectedResponse);
	}
	
	@Test 
	public void testDefaultPageResponse(){
		//if no page is specified it will go to default page if found
		String requestedResource="GET / HTTP/1.1\r\n"
				+ "Host: localhost:10008\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:81.0) Gecko/20100101 Firefox/81.0\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n"
				+ "Accept-Language: en-US,en;q=0.5\r\n"
				+ "Accept-Encoding: gzip, deflate\r\n"
				+ "Connection: keep-alive\r\n"
				+ "Upgrade-Insecure-Requests: 1\r\n"
				+ "Cache-Control: max-age=0";
		String expectedResponse="";
		String response=server.response(requestedResource);
		assertEquals(response, expectedResponse);	
	}
	
	@Test
	public void testMaintenanceResponse() {
		//respond to all requests with a specified page
		String requestedResource="GET / HTTP/1.1\r\n"
				+ "Host: localhost:10008\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:81.0) Gecko/20100101 Firefox/81.0\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n"
				+ "Accept-Language: en-US,en;q=0.5\r\n"
				+ "Accept-Encoding: gzip, deflate\r\n"
				+ "Connection: keep-alive\r\n"
				+ "Upgrade-Insecure-Requests: 1\r\n"
				+ "Cache-Control: max-age=0";
		String expectedResponse="";
		String response=server.response(requestedResource);
		assertEquals(response, expectedResponse);
	}

}
