package hu.bme.mit.train.tachograph;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.*;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.system.TrainSystem;

public class TachographTest {

	Tachograph tacho;
	TrainController controller;
	TrainSensor sensor;
	TrainUser user;
	
	@Before
	public void before() {
		TrainSystem system = new TrainSystem();
		controller = system.getController();
		sensor = system.getSensor();
		user = system.getUser();
		tacho =  new Tachograph(controller, user);
		sensor.overrideSpeedLimit(50);
	}
	
	
	@Test
	public void OverridingJoystickPosition_CheckSpeedLimit() {
		user.overrideJoystickPosition(4);
		controller.followSpeed();
		tacho.record();
		Assert.assertEquals(1, tacho.getRecordCount());
	}
}
