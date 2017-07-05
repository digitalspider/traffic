package au.com.traffic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import au.com.traffic.model.Light;
import au.com.traffic.model.State;
import au.com.traffic.model.TrafficState;

@Component
public class TrafficServiceImpl implements TrafficService {
	private static TrafficSchedulerThread trafficSchedulerThread;

	@Autowired
	private LightService lightService;

	@Override
	public void switchState(List<Light> lights, int elapsedTimeInSeconds) {
		boolean switchGreen = false;
		boolean switchAll = false;
		if (elapsedTimeInSeconds % 30 == 27) {
			switchGreen = true;
		}
		if (elapsedTimeInSeconds % 30 == 0) {
			switchAll = true;
		}
		for (Light light : lights) {
			if (switchAll && State.ORANGE.equals(light.getState())) {
				lightService.changeState(light);
			}
			if (switchGreen && State.GREEN.equals(light.getState())) {
				lightService.changeState(light);
			}
		}
	}

	@Override
	public void start() {
		if (trafficSchedulerThread == null) {
			trafficSchedulerThread = new TrafficSchedulerThread(this, TrafficState.getInstance().getLights());
			trafficSchedulerThread.start();
		}
	}

	@Override
	public void stop() {
		if (trafficSchedulerThread != null) {
			trafficSchedulerThread.setRunning(false);
			trafficSchedulerThread = null;
		}
	}

	public void setLightService(LightService lightService) {
		this.lightService = lightService;
	}

	@Override
	public int getElapsedTimeInSeconds() {
		return trafficSchedulerThread != null ? trafficSchedulerThread.getElapsedTimeInSeconds() : 0;
	}

	@Override
	public boolean isStarted() {
		return trafficSchedulerThread != null;
	}

}
