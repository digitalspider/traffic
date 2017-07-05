package au.com.traffic.model;

public enum Orientation {
	NORTH_SOUTH("NorthSouth"),
	EAST_WEST("EastWest");

	private String name;

	Orientation(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
