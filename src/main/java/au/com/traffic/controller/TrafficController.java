package au.com.traffic.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import au.com.traffic.model.Light;
import au.com.traffic.model.TrafficState;
import au.com.traffic.service.LightService;
import au.com.traffic.service.TrafficService;

@RestController
@RequestMapping("/traffic")
public class TrafficController {
	private static final String TEMPLATE_TRAFFIC = "/templates/traffic.vm";

	private static Logger LOG = Logger.getLogger(TrafficController.class);

	private static final String TEMPLATE_SINGLE = "Light: %s. See <a href='/traffic'>traffic</a>";

	private static final String META_REDIRECT_TO_TRAFFIC = "<meta http-equiv='refresh' content='3;url=/traffic'/>";

	private TrafficState trafficState = TrafficState.getInstance();
	private VelocityEngine ve = new VelocityEngine();

	@Autowired
	private LightService lightService;

	@Autowired
	private TrafficService trafficService;

	public TrafficController() {
		ve.init();
	}

	@RequestMapping("/start")
	public String start() throws FileNotFoundException, IOException, Exception {
		trafficService.start();
		return META_REDIRECT_TO_TRAFFIC + "Started traffic simulation. See <a href='/traffic'>traffic</a>";
	}

	@RequestMapping("/stop")
	public String stop() throws FileNotFoundException, IOException, Exception {
		trafficService.stop();
		return META_REDIRECT_TO_TRAFFIC + "Stopped traffic simulation. See <a href='/traffic'>traffic</a>";
	}

	@RequestMapping("")
	public String watch() throws FileNotFoundException, IOException, Exception {
//		Template template = ve.getTemplate("traffic.vm");
		VelocityContext context = new VelocityContext();
		context.put("lights", trafficState.getLights());
		context.put("elapsedTime", trafficService.getElapsedTimeInSeconds());
		context.put("simStarted", trafficService.isStarted());
		StringWriter writer = new StringWriter();
//		template.merge(context, writer);
		String logTag = "template.vm";
		InputStream is = getClass().getResourceAsStream(TEMPLATE_TRAFFIC);
		ve.evaluate(context, writer, logTag, new InputStreamReader(is));
		LOG.debug(writer.toString());
		return writer.toString();
	}

	@RequestMapping("/light/{id}")
	public String getState(@PathVariable(value = "id") int id) throws FileNotFoundException, IOException, Exception {
		String result = "";
		if (id > 0) {
			result = lightService.getLight(id).toString();
		}
		return String.format(TEMPLATE_SINGLE, result);
	}

	@RequestMapping(value = { "/switch/{id}" }, method = RequestMethod.GET)
	public String switchState(@PathVariable(value = "id") int id) throws FileNotFoundException, IOException, Exception {
		String result = "";
		if (id > 0) {
			Light light = lightService.getLight(id);

			LOG.debug("light=" + light);
			if (light != null) {
				lightService.changeState(light);
				result = light.toString();
			}
		}
		return String.format(TEMPLATE_SINGLE, result);
	}

	public LightService getLightService() {
		return lightService;
	}

	public void setLightService(LightService lightService) {
		this.lightService = lightService;
	}

	public void setTrafficState(TrafficState trafficState) {
		this.trafficState = trafficState;
	}

	public void setTrafficService(TrafficService trafficService) {
		this.trafficService = trafficService;
	}

}
