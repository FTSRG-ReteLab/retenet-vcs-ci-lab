package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.user.TrainUserImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.Console;

import static org.mockito.Mockito.*;

public class TrainSensorTest {
    TrainController controller;
    TrainSensor sensor;
    TrainUserImpl user;


    @Before
    public void before() {
        controller = mock(TrainController.class);
        user = mock(TrainUserImpl.class);
        sensor = new TrainSensorImpl(controller,user);
    }

    @Test
    public void AlarmUnder0() {
        sensor.overrideSpeedLimit(-1);
        verify(user,times(1)).setAlarmState(true);
    }

    @Test
    public void AlarmOver500() {
        sensor.overrideSpeedLimit(501);
        verify(user,times(1)).setAlarmState(true);
    }

    @Test
    public void AlarmOverDouble() {
        when(controller.getReferenceSpeed()).thenReturn(100);
        sensor.overrideSpeedLimit(40);
        verify(user,times(1)).setAlarmState(true);
    }

    @Test
    public void AlarmBetweenLimits() {
        sensor.overrideSpeedLimit(101);
        verify(user,times(1)).setAlarmState(false);
    }


}
