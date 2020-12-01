package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import exceptions.parsers_exceptions.InvalidRequestException;
import exceptions.webserver_exceptions.WebServerStateTransitionException;
import handlers.RequestHandler;
import parsers.RequestParser;
import parsers.ResponseParser;
import webserver.Request;
import webserver.WebServerState;

public class ResponeParserTest {
	
	ResponseParser validResponse, homePageResponse, pageNotFoundResponse;
	String rootDir;
	
	@Before
	public void init() throws InvalidRequestException, IOException {
	
		String requests[]= {"GET / HTTP/1.1\r\n"
				+ "Host: 127.0.0.1:10008\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:81.0) Gecko/20100101 Firefox/81.0\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n"
				+ "Accept-Language: en-US,en;q=0.5\r\n"
				+ "Accept-Encoding: gzip, deflate\r\n"
				+ "Connection: keep-alive\r\n"
				+ "Upgrade-Insecure-Requests: 1",
				
				"GET /index.html HTTP/1.1\r\n"
				+ "Host: localhost:10008\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:81.0) Gecko/20100101 Firefox/81.0\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n"
				+ "Accept-Language: en-US,en;q=0.5\r\n"
				+ "Accept-Encoding: gzip, deflate\r\n"
				+ "Connection: keep-alive\r\n"
				+ "Upgrade-Insecure-Requests: 1",
				
				"GET /page1.html HTTP/1.1\r\n"
				+ "Host: localhost:10008\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:81.0) Gecko/20100101 Firefox/81.0\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n"
				+ "Accept-Language: en-US,en;q=0.5\r\n"
				+ "Accept-Encoding: gzip, deflate\r\n"
				+ "Connection: keep-alive\r\n"
				+ "Upgrade-Insecure-Requests: 1"};
	
		this.rootDir = "..\\WebServer\\WebserverTestingDirectories\\RootDirectory\\htdocs";
		this.homePageResponse= new ResponseParser(rootDir,RequestHandler.getRequest(new RequestParser(requests[0])));
		this.validResponse= new ResponseParser(rootDir,RequestHandler.getRequest(new RequestParser(requests[1])));
		this.pageNotFoundResponse= new ResponseParser(rootDir,RequestHandler.getRequest(new RequestParser(requests[2])));
	}

	
	@Test
	public void testHttpVersion(){
		assertEquals("HTTP/1.1",validResponse.getHttpVersion());
		assertEquals("HTTP/1.1",homePageResponse.getHttpVersion());
		assertEquals("HTTP/1.1",pageNotFoundResponse.getHttpVersion());
	}
	
	@Test
	public void testStatusCode() throws WebServerStateTransitionException{
		WebServerState.setStopped();
		assertEquals("502 Service Unavailable",validResponse.getStatusCode());
		assertEquals("502 Service Unavailable",homePageResponse.getStatusCode());
		assertEquals("502 Service Unavailable",pageNotFoundResponse.getStatusCode());
		
		WebServerState.setRunning();
		assertEquals("200 OK",validResponse.getStatusCode());
		assertEquals("200 OK",homePageResponse.getStatusCode());
		assertEquals("404 Not Found",pageNotFoundResponse.getStatusCode());
		
		WebServerState.setMaintenance();
		assertEquals("502 Service Unavailable",validResponse.getStatusCode());
		assertEquals("502 Service Unavailable",homePageResponse.getStatusCode());
		assertEquals("502 Service Unavailable",pageNotFoundResponse.getStatusCode());
	}
	
	@Test
	public void testContentType() {
		
	}
	
	@Test
	public void testValidResponseContent() throws IOException {
		String expected ="<html>\r\n"
				+ " <p>Welcome to the default page!</p>\r\n"
				+ "</html>";
		assertEquals(expected,homePageResponse.getContent());
		assertEquals(expected,validResponse.getContent());
	}
	
	@Test
	public void testPageNotFoundResponseContent() throws IOException {
		String expected ="<html>\r\n"
				+ " <p>Page was not found!</p>\r\n"
				+ "</html>";
		assertEquals(expected,pageNotFoundResponse.getContent());
	}

}
