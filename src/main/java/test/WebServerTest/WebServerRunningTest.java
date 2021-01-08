package test.WebServerTest;

import exceptions.webserver_exceptions.WebServerStateTransitionException;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import webserver.WebServerState;

import static com.thoughtworks.selenium.SeleneseTestNgHelper.assertEquals;

public class WebServerRunningTest {

    String address = "http://localhost:10008";

    @Test
    public void testIndexPage() throws WebServerStateTransitionException {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get(address);
        assertEquals("Running",webDriver.getTitle());
    }

    @Test
    public void testPage1() throws WebServerStateTransitionException {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get(address+"/page1.html");
        assertEquals("Running (page 1)",webDriver.getTitle());
    }

    @Test
    public void testPage2() throws WebServerStateTransitionException {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get(address+"/page2.html");
        assertEquals("Running (page 2)",webDriver.getTitle());
    }

    @Test
    public void testInvalidPage() throws WebServerStateTransitionException {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get(address+"/invalid.html");
        assertEquals("Running (404)",webDriver.getTitle());
    }

    @Test
    public void testButtonClickPage() throws WebServerStateTransitionException {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get(address+"/pageNotFound.html");
    }

}
