package au.com.traffic.service;

import java.util.List;

import au.com.traffic.model.Light;

public interface TrafficService {
	/**
	 * Start the simulation
	 */
	void start();

	/**
	 * Stop the simulation
	 */
	void stop();

	/**
	 * Return true if the simulation is running
	 */
	boolean isStarted();

	/**
	 * Get number of seconds since the simulation last started
	 */
	int getElapsedTimeInSeconds();

	/**
	 * Switch the states of the lights based on the elaspsedTimeInSeconds.
	 * This is done using {@link LightService#changeState(Light)}
	 * 
	 * @param lights
	 * @param elapsedTimeInSeconds
	 */
	void switchState(List<Light> lights, int elapsedTimeInSeconds);
}
