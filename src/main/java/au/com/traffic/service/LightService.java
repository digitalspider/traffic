package au.com.traffic.service;

import au.com.traffic.model.Light;

public interface LightService {
	/**
	 * Get the current light implementation given a light ID.
	 * 
	 * @param id
	 * @return
	 */
	Light getLight(int id);

	/**
	 * Change the state of the light, from GREEN -> ORANGE -> RED -> GREEN.
	 * When transitioning from ORANGE to RED, the "linked light" also changes to GREEN.
	 * 
	 * @param light the light whose state should change
	 */
	void changeState(Light light);

	/**
	 * Link two lights together, by setting their corresponding linkedLightIds.
	 * 
	 * @param light1
	 * @param light2
	 */
	void linkLights(Light light1, Light light2);
}
