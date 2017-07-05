package au.com.traffic.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import au.com.traffic.model.Light;
import au.com.traffic.model.State;

public class LightServiceImplTest {

	private LightService lightService;

	@Before
	public void setup() {
		lightService = Mockito.spy(new LightServiceImpl());
	}

	@Test
	public void changeState_shouldBeOrangeIfStateIsGreen() {
		Light light = new Light();
		light.setId(1);
		light.setState(State.GREEN);
		lightService.changeState(light);
		Assert.assertEquals(State.ORANGE, light.getState());
	}

	@Test
	public void changeState_shouldBeGreenIfStateIsRed() {
		Light light = new Light();
		light.setId(1);
		light.setState(State.RED);
		lightService.changeState(light);
		Assert.assertEquals(State.GREEN, light.getState());
	}

	@Test
	public void changeState_shouldBeRedIfStateIsOrangeAndNoLinkedLight() {
		Light light = new Light();
		light.setId(1);
		light.setState(State.ORANGE);
		lightService.changeState(light);
		Assert.assertEquals(State.RED, light.getState());
	}

	@Test
	public void changeState_shouldBeRedIfStateIsOrangeAndLinkedLightShouldBeGreen() {
		Light light = new Light();
		light.setId(1);
		light.setState(State.ORANGE);
		Light linkedLight = new Light();
		linkedLight.setId(2);
		lightService.linkLights(light, linkedLight);
		Mockito.doReturn(linkedLight).when(lightService).getLight(2);
		lightService.changeState(light);

		Assert.assertEquals(State.RED, light.getState());
		Assert.assertEquals(State.GREEN, linkedLight.getState());

	}
}
