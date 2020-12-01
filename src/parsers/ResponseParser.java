package parsers;

import java.io.IOException;

import config.DirectoryManager;
import config.FileManager;
import validators.RootDirectoryValidator;
import webserver.DefaultValues;
import webserver.Request;
import webserver.WebServerState;

public class ResponseParser {
	
	private String rootDirectoryPath;
	private Request request;
	
	public ResponseParser(String rootDirectoryPath, Request request) {
		this.rootDirectoryPath = rootDirectoryPath;
		this.request = request;
	}
	
	public String getHttpVersion() {
		return request.getHttpVersion();
	}
	
	public String getStatusCode() {
		if(WebServerState.isStopped()) {
			return "502 Service Unavailable";
		}
		if(WebServerState.isMaintenance()) {
			return "502 Service Unavailable";
		}
		if(WebServerState.isRunning()) {
			if(isPageNotFound())
				return "404 Not Found";
			return "200 OK";
		}
		return "502 Service Unavailable";
	}
	
	public String getContentType() {
		 String contentType="text/plain";
		 if (request.getResourcePath().endsWith(".html") || request.getResourcePath().endsWith(".html"))
			 contentType="text/html";
		 else if (request.getResourcePath().endsWith(".jpg") || request.getResourcePath().endsWith(".jpeg"))
			 contentType="image/jpeg";
		 else if (request.getResourcePath().endsWith(".gif"))
			 contentType="image/gif";
		 else if (request.getResourcePath().endsWith(".class"))
		     contentType="application/octet-stream";

		 if(isPageNotFound()) {
			 contentType="text/html";
		 }
		 
		 return contentType;
	}
	
	public String getContent() throws IOException {
		String resultPath;
		
		if(request.getResourcePath().equals("/")) {
			resultPath = rootDirectoryPath + "/" + DefaultValues.getHomePage();
		}
		else if(FileManager.isFile(rootDirectoryPath + request.getResourcePath())) {
			resultPath = rootDirectoryPath + "/" + request.getResourcePath();
		}
		else {
			resultPath = rootDirectoryPath  + "/" +  DefaultValues.getpageNotFound();
		}
		return FileManager.getContent(resultPath);		
	}
	
	public boolean isPageNotFound() {
		if(request.getResourcePath().equals("/")) {
			return false;
		}
		else if(FileManager.isFile(rootDirectoryPath + request.getResourcePath())) {
			return false;
		}
		else {
			return true;
		}
	}
}
