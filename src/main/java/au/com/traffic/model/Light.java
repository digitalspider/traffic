package au.com.traffic.model;

import static au.com.traffic.model.Orientation.EAST_WEST;
import static au.com.traffic.model.Orientation.NORTH_SOUTH;
import static au.com.traffic.model.State.GREEN;
import static au.com.traffic.model.State.RED;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class Light {
	private int id;
	@SerializedName("state")
	private State state;
	private Orientation orientation;
	private int linkedLightId;

	public Light() {
		this.id = 0;
		this.state = GREEN;
		this.orientation = NORTH_SOUTH;
	}

	public Light createConverse() {
		Light light = new Light();
		int converseId = this.id + 1;
		this.setLinkedLightId(converseId);
		light.setId(converseId);
		light.setState(state == GREEN ? RED : GREEN);
		light.setOrientation(orientation == NORTH_SOUTH ? EAST_WEST : NORTH_SOUTH);
		light.setLinkedLightId(this.id);
		return light;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public int getLinkedLightId() {
		return linkedLightId;
	}

	public void setLinkedLightId(int linkedLightId) {
		this.linkedLightId = linkedLightId;
	}
}
