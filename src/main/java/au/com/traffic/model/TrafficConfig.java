package au.com.traffic.model;

import com.google.gson.Gson;

public class TrafficConfig {

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
