package com.monocept.Spring.model;

import org.springframework.stereotype.Component;

@Component
public class BadmintonCoach implements ICoach {

	@Override
	public String dailyWorkout() {
		// TODO Auto-generated method stub
		return "BadminCoach advised you";
	}

}
