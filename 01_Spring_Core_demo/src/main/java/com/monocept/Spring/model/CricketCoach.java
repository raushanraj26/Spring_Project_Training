package com.monocept.Spring.model;

import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements ICoach {

	@Override
	public String dailyWorkout() {
//		System.out.println("Cricket Coach Advised you!");
		return "Cricket coach advised you";
	}

}
