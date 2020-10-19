package test;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.WebServerStateTransitionException;
import webserver.WebServerState;

public class WebServerStateTest {

	@Test
	public void testValidIsStoppedWhenInitialized() {
		WebServerState state = new WebServerState();
		assertTrue(state.isStopped());
	}

	@Test
	public void testValidSetRunningFromStopped() throws WebServerStateTransitionException {
		WebServerState state = new WebServerState(); //state is stopped
		state.setRunning();		
	}

	@Test (expected = WebServerStateTransitionException.class)
	public void testInvalidSetMaintenanceFromStopped() throws WebServerStateTransitionException{
		WebServerState state = new WebServerState(); //state is stopped
		state.setMaintenance();		
	}
	
	@Test 
	public void testValidSetMaintenanceFromRunning() throws WebServerStateTransitionException{
		WebServerState state = new WebServerState(); //state is stopped
		state.setRunning();
		state.setMaintenance();		
	}
	
	@Test 
	public void testValidSetRunningFromMaintenance() throws WebServerStateTransitionException{
		WebServerState state = new WebServerState(); //state is stopped
		state.setRunning();	
		state.setMaintenance(); //state is in maintenance
		state.setRunning();		
	}

}
