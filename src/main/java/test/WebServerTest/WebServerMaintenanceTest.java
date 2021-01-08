package test.WebServerTest;

import config.Persist;
import exceptions.config_exceptions.ConfigurationException;
import exceptions.webserver_exceptions.WebServerStateTransitionException;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import webserver.WebServerState;

import java.io.IOException;

import static com.thoughtworks.selenium.SeleneseTestNgHelper.assertEquals;

public class WebServerMaintenanceTest {

    String address = "http://localhost:10008";

    @Test
    public void testIndexPage() throws WebServerStateTransitionException {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get(address);
        assertEquals("Maintenance",webDriver.getTitle());
    }

    @Test
    public void testPage1() throws WebServerStateTransitionException {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get(address+"/page1.html");
        assertEquals("Maintenance",webDriver.getTitle());
    }

    @Test
    public void testPage2() throws WebServerStateTransitionException {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get(address+"/page2.html");
        assertEquals("Maintenance",webDriver.getTitle());
    }

    @Test
    public void testInvalidPage() throws WebServerStateTransitionException {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get(address+"/invalid.html");
        assertEquals("Maintenance",webDriver.getTitle());
    }


}
