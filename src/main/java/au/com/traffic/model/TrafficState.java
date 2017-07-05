package au.com.traffic.model;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import au.com.traffic.controller.TrafficController;

@Component
@Scope(scopeName = "application")
public class TrafficState {
	private static final Logger LOG = Logger.getLogger(TrafficState.class);

	private static final String TRAFFIC_JSON_FILENAME = "/traffic.json";

	private static TrafficState trafficState;

	private TrafficState() {
	}

	public static TrafficState getInstance() {
		if (trafficState == null) {
			trafficState = TrafficState.init();
		}
		return trafficState;
	}

	@SerializedName("config")
	private TrafficConfig config;
	private List<Light> lights;

	private Map<Integer, Light> lightMap = new HashMap<>();

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	public TrafficConfig getConfig() {
		return config;
	}

	public void setConfig(TrafficConfig config) {
		this.config = config;
	}

	public List<Light> getLights() {
		return lights;
	}

	public void setLights(List<Light> lights) {
		this.lights = lights;
		updateLights(lights);
	}

	public void updateLights(List<Light> lights) {
		lightMap.clear();
		for (Light light : lights) {
			if (light != null) {
				lightMap.put(light.getId(), light);
			}
		}
	}

	public Light getLight(int id) {
		return lightMap.get(id);
	}

	private static TrafficState init() {
		try {
			InputStream is = TrafficController.class.getResourceAsStream(TRAFFIC_JSON_FILENAME);
			Gson gson = new Gson();
			TrafficState state = gson.fromJson(new InputStreamReader(is), TrafficState.class);
			LOG.info("state=" + state);
			if (state == null) {
				throw new Exception("Could not read input file " + TRAFFIC_JSON_FILENAME);
			}
			state.updateLights(state.getLights());
			return state;
		} catch (Exception e) {
			LOG.error(e, e);
			System.exit(1);
		}
		return null;
	}

	public Map<Integer, Light> getLightMap() {
		return lightMap;
	}
}
