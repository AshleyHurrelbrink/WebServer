package webserver;

import exceptions.webserver_exceptions.WebServerStateTransitionException;
import validators.StateValidator;

public class WebServerState {
	
	private static final int stopped =1 ;
	private static final int running = 2;
	private static final int maintenance = 3;
	
	private int state;
	
	public WebServerState() {
		this.state=stopped;
	}
	
	public boolean isStopped() {
		return (this.state==stopped)? true: false;
	}
	
	public boolean isRunning() {
		return (this.state==running)? true: false;
	}
	
	public boolean isMaintenance() {
		return (this.state==maintenance)? true: false;
	}
	
	public void setStopped() throws WebServerStateTransitionException {
		if(!StateValidator.validateSetStopped(stopped)) {
			throw new WebServerStateTransitionException();
		}
		this.state=stopped;
	}
	
	public void setRunning() throws WebServerStateTransitionException {
		if(!StateValidator.validateSetStopped(running)) {
			throw new WebServerStateTransitionException();
		}
		this.state=running;
	}
	
	public void setMaintenance() throws WebServerStateTransitionException {
		if(!StateValidator.validateSetStopped(maintenance)) {
			throw new WebServerStateTransitionException();
		}
		this.state=maintenance;
	}
	
	
}
