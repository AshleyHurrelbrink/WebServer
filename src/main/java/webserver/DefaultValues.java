package webserver;

public class DefaultValues {
	private static String homePage = "index.html";
	private static String pageNotFound = "pageNotFound.html";
	public static int debug=1;
	
	public static String getHomePage() {
		return homePage;
	}
	public static String getpageNotFound() {
		return pageNotFound;
	}
}
