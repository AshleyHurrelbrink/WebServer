package validators;

public class RequestParserValidator {

	public static boolean validateResource(String resource) {
        return resource.startsWith("/");
    }
	
	public static boolean validateHost(String host) {
        return host.startsWith("localhost:") || host.startsWith("127.0.0.1:");
    }
	
	public static boolean validateMethod(String method) {
        return method.equals("GET");
    }
	
	public static boolean validateHttpVersion(String http) {
        return http.equals("HTTP/1.1");
    }
}
