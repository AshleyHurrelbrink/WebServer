package test;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.parsers_exceptions.InvalidRequestParserException;
import parsers.RequestParser;

public class RequestParserTest {

	@Test
	public void testValidResponse() throws InvalidRequestParserException {
		String validRequests[]= {"GET / HTTP/1.1\r\n"
				+ "Host: localhost:10008\r\n"
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
				
				"GET /www_root/page1.html HTTP/1.1\r\n"
				+ "Host: localhost:10008\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:81.0) Gecko/20100101 Firefox/81.0\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n"
				+ "Accept-Language: en-US,en;q=0.5\r\n"
				+ "Accept-Encoding: gzip, deflate\r\n"
				+ "Connection: keep-alive\r\n"
				+ "Upgrade-Insecure-Requests: 1",
				
				"GET /www_maintenance/maintenance.html HTTP/1.1\r\n"
				+ "Host: localhost:10008\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:81.0) Gecko/20100101 Firefox/81.0\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n"
				+ "Accept-Language: en-US,en;q=0.5\r\n"
				+ "Accept-Encoding: gzip, deflate\r\n"
				+ "Connection: keep-alive\r\n"
				+ "Upgrade-Insecure-Requests: 1"};
						
		for(String request: validRequests) {
				RequestParser requestParser= new RequestParser(request);
				requestParser.getResource();
		}		
	}
	
	@Test (expected = InvalidRequestParserException.class)
	public void testInvalidResponse() throws InvalidRequestParserException {
		String validRequests[]= {"GET HTTP/1.1\r\n"
				+ "Host: localhost:10008\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:81.0) Gecko/20100101 Firefox/81.0\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n"
				+ "Accept-Language: en-US,en;q=0.5\r\n"
				+ "Accept-Encoding: gzip, deflate\r\n"
				+ "Connection: keep-alive\r\n"
				+ "Upgrade-Insecure-Requests: 1",
				
				"GET index.html HTTP/1.1\r\n"
				+ "Host: localhost:10008\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:81.0) Gecko/20100101 Firefox/81.0\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n"
				+ "Accept-Language: en-US,en;q=0.5\r\n"
				+ "Accept-Encoding: gzip, deflate\r\n"
				+ "Connection: keep-alive\r\n"
				+ "Upgrade-Insecure-Requests: 1",
				
				"GET /index.txt HTTP/1.1\r\n"
				+ "Host: localhost:10008\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:81.0) Gecko/20100101 Firefox/81.0\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n"
				+ "Accept-Language: en-US,en;q=0.5\r\n"
				+ "Accept-Encoding: gzip, deflate\r\n"
				+ "Connection: keep-alive\r\n"
				+ "Upgrade-Insecure-Requests: 1"};
		
		for(String request: validRequests) {
			RequestParser requestParser= new RequestParser(request);
			requestParser.getResource();
		}		
	}
	
	@Test 
	public void testValidHost() throws InvalidRequestParserException {
		String validRequests[]= {"GET HTTP/1.1\r\n"
				+ "Host: localhost:10008\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:81.0) Gecko/20100101 Firefox/81.0\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n"
				+ "Accept-Language: en-US,en;q=0.5\r\n"
				+ "Accept-Encoding: gzip, deflate\r\n"
				+ "Connection: keep-alive\r\n"
				+ "Upgrade-Insecure-Requests: 1",
				
				"GET HTTP/1.1\r\n"
				+ "Host: 127.0.0.1:10008\r\n"
				+ "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:81.0) Gecko/20100101 Firefox/81.0\r\n"
				+ "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\r\n"
				+ "Accept-Language: en-US,en;q=0.5\r\n"
				+ "Accept-Encoding: gzip, deflate\r\n"
				+ "Connection: keep-alive\r\n"
				+ "Upgrade-Insecure-Requests: 1"};
		
		for(String request: validRequests) {
			RequestParser requestParser= new RequestParser(request);
			requestParser.getHost();
		}		
	}

}
