package com.monocept.Spring.model;
import org.springframework.stereotype.Component;

@Component
public class FootballCoach implements ICoach {

	@Override
	public String dailyWorkout() {
		// TODO Auto-generated method stub
		return "Football Coach Advised you";
	}

}
