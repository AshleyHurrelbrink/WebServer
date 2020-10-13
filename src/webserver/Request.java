package webserver;

public class Request {
	
	String requestedResource;
	String[] requestedResourceSplit;
	String url, host;
	
	public Request(String requestedResource) {
		this.requestedResource=requestedResource;
		this.requestedResourceSplit = requestedResource.split("\r\n");
		this.url = requestedResourceSplit[0];
		this.host = requestedResourceSplit[1];
		//add other
	}
	
	public boolean isUrlValid() {
		return false;
	}
	
	public boolean isHostValid() {
		return false;
	}
	
	public boolean isPageNotFoundUrl() {
		return false;
	}
	
	public boolean isDefaultUrl() {
		return false;
	}
	
	
}
