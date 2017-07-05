package au.com.traffic.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LightTest {

	@Test
	public void testEmptyLight() {
		Light light = new Light();
		assertEquals("{'id':0,'state':'GREEN','orientation':'NORTH_SOUTH','linkedLightId':0}".replaceAll("'", "\""), light.toString());
	}

	@Test
	public void testPopulatedLight() {
		Light light = new Light();
		light.setId(1);
		light.setOrientation(Orientation.NORTH_SOUTH);
		light.setState(State.GREEN);
		light.setLinkedLightId(2);
		assertEquals("{'id':1,'state':'GREEN','orientation':'NORTH_SOUTH','linkedLightId':2}".replaceAll("'", "\""), light.toString());
	}

	@Test
	public void testCreateConverse() {
		Light light = new Light();
		light.setId(7);
		light.setOrientation(Orientation.NORTH_SOUTH);
		light.setState(State.GREEN);
		Light converseLight = light.createConverse();
		assertEquals("{'id':8,'state':'RED','orientation':'EAST_WEST','linkedLightId':7}".replaceAll("'", "\""), converseLight.toString());
	}

}
