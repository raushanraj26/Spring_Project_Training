package com.monocept.Spring.model;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class  VolleyballCoach implements ICoach {

	@Override
	public String dailyWorkout() {
		// TODO Auto-generated method stub
		return "VolleyBallCoach advised you";
	}
	

}
