package webserver;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import exceptions.config_exceptions.ConfigurationException;
import exceptions.webserver_exceptions.WebServerStateTransitionException;
import validators.StateValidator;

public class WebServerState extends Thread {

	private static final int stopped =1 ;
	private static final int running = 2;
	private static final int maintenance = 3;
	
	private static int state=stopped;
	
	public static boolean isStopped() {
		return state == stopped;
	}
	
	public static boolean isRunning() {
		return state == running;
	}
	
	public static boolean isMaintenance() {
		return state == maintenance;
	}
	
	public static void setStopped() throws WebServerStateTransitionException {
		if(!StateValidator.validateSetStopped(state)) {
			throw new WebServerStateTransitionException();
		}
		state=stopped;
	}
	
	public static void setRunning() throws WebServerStateTransitionException {
		if(!StateValidator.validateSetRunning(state)) {
			throw new WebServerStateTransitionException();
		}
		state=running;
	}
	
	public static void setMaintenance() throws WebServerStateTransitionException {
		if(!StateValidator.validateSetMaintenance(state)) {
			throw new WebServerStateTransitionException();
		}
		state=maintenance;
	}
	
	public static String getCurrentState() {
		if(state==stopped) 
			return "WebServer is stopped";
		if(state==running) 
			return "WebServer is running";
		if(state==maintenance) 
			return "WebServer is in maintenance";
		return "State is undefined";
	}

	public static String getStatus() {
		if(state==stopped)
			return "stopped";
		if(state==running)
			return "running";
		if(state==maintenance)
			return "maintenance";
		return "State is undefined";
	}



}
