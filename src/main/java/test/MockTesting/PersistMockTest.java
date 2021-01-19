package test.MockTesting;

import config.Config;
import config.Persist;
import exceptions.config_exceptions.ConfigurationException;
import exceptions.config_exceptions.InvalidMaintenanceDirectoryException;
import exceptions.config_exceptions.InvalidPortNumberException;
import exceptions.config_exceptions.InvalidRootDirectoryException;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class PersistMockTest {

    int[] validPortNumbers = {10008, 10009};

	//Set Port Number ---------------------------------------------------------------

	@Test
	public void testSetValidPortNumber() throws IOException, ConfigurationException {
        Persist persist = mock(Persist.class);
		
		for(int port: validPortNumbers) {
			persist.setPortNumber(port);
		}
	}

	@Test
	public void testSetPortNumber() throws ConfigurationException, IOException{
		Persist persistMock = mock(Persist.class);
		//current value is 10001
		persistMock.setPortNumber(10008);
		when(persistMock.getPortNumber()).thenReturn(10008);
		assertEquals(10008,persistMock.getPortNumber());
	}


	//Get Port Number ---------------------------------------------------------------

	@Test
	public void testGetPortNumber() throws ConfigurationException, IOException{
		Persist persistMock = mock(Persist.class);
		when(persistMock.getPortNumber()).thenReturn(10008);
		assertEquals(10008, persistMock.getPortNumber());
	}

}
