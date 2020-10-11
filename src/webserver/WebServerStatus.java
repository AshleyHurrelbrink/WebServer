package webserver;

public class WebServerStatus {
	
	private static final int stopped =1 ;
	private static final int running = 2;
	private static final int maintenance = 3;
	
	private int status;
	
	public WebServerStatus() {
		status=stopped;
	}
	
	public boolean isStopped() {
		return (status==stopped)? true: false;
	}
	
	public boolean isRunning() {
		return (status==running)? true: false;
	}
	
	public boolean isMaintenance() {
		return (status==maintenance)? true: false;
	}
	
	public void setStopped() {
		status=stopped;
	}
	
	public void setRunning() {
		status=running;
	}
	
	public void setMaintenance() {
		status=maintenance;
	}
	
	
}
