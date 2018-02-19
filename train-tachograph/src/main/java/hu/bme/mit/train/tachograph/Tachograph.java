package hu.bme.mit.train.tachograph;

import java.time.LocalDateTime;

import com.google.common.collect.Table;
import hu.bme.mit.train.interfaces.*;

public class Tachograph {
	private Table<LocalDateTime, Integer, Integer> records;
	private TrainController TC;
	private TrainUser TU;
	
	public void record(){
		records.put(LocalDateTime.now(),TU.getJoystickPosition(),TC.getReferenceSpeed());
	}
	
	public Tachograph(TrainController tController, TrainUser tUser){
		TC = tController;
		TU = tUser;
	}
}
