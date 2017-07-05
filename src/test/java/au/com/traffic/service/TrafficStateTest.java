package au.com.traffic.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import au.com.traffic.model.State;
import au.com.traffic.model.TrafficState;

public class TrafficStateTest {

	@Test
	public void init() throws FileNotFoundException, IOException, Exception {
		TrafficState state = TrafficState.getInstance();
		assertNotNull(state);
		assertEquals(2, state.getLights().size());
		assertEquals(State.GREEN, state.getLight(1).getState());
		assertEquals(State.RED, state.getLight(2).getState());
	}
}
