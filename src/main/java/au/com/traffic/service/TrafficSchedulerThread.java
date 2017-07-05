package au.com.traffic.service;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import au.com.traffic.model.Light;

public class TrafficSchedulerThread extends Thread {

	private static final Logger LOG = Logger.getLogger(TrafficSchedulerThread.class);
	private TrafficService trafficService;
	private List<Light> lights;
	private boolean running = true;
	private int elapsedTimeInSeconds;

	public TrafficSchedulerThread(TrafficService trafficService, List<Light> lights) {
		this.trafficService = trafficService;
		this.lights = lights;
	}

	@Override
	public void run() {
		LOG.info("Thread started");
		long startTime = new Date().getTime();
		while (running) {
			elapsedTimeInSeconds = (int) ((new Date().getTime() - startTime) / 1000);
			if (elapsedTimeInSeconds > 0 && elapsedTimeInSeconds % 3 == 0) {
				LOG.info("elapsedTimeInSeconds=" + elapsedTimeInSeconds);
				trafficService.switchState(lights, elapsedTimeInSeconds);
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				LOG.error(e, e);
			}
		}
		LOG.info("Thread ended");
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public int getElapsedTimeInSeconds() {
		return elapsedTimeInSeconds;
	}

}
