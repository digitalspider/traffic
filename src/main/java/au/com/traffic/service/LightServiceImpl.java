package au.com.traffic.service;

import org.springframework.stereotype.Component;

import au.com.traffic.model.Light;
import au.com.traffic.model.State;
import au.com.traffic.model.TrafficState;

@Component
public class LightServiceImpl implements LightService {

	@Override
	public void changeState(Light light) {
		synchronized (light) {
			switch (light.getState()) {
			case RED:
				light.setState(State.GREEN);
				break;
			case ORANGE:
				light.setState(State.RED);
				Light linkedLight = getLight(light.getLinkedLightId());
				if (linkedLight != null) {
					linkedLight.setState(State.GREEN);
				}
				break;
			case GREEN:
				light.setState(State.ORANGE);
				break;
			}
		}
	}

	@Override
	public Light getLight(int id) {
		return TrafficState.getInstance().getLight(id);
	}

	@Override
	public void linkLights(Light light1, Light light2) {
		light1.setLinkedLightId(light2.getId());
		light2.setLinkedLightId(light1.getId());
	}

}
