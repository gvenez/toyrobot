package com.anz.toyrobot;

import org.springframework.web.client.RestTemplate;

import com.anz.toyrobot.models.Angle;
import com.anz.toyrobot.models.Position;
import com.anz.toyrobot.models.Robot;

public class RobotTester {

	 public static final String SERVER_URI = "http://localhost:8080/toyrobot/";
	 
	 public static void main(String args[]){
		 
	 }
	 
	 private static void testCreateRobot()
	 {
		 RestTemplate restTemplate = new RestTemplate();
		String name="newRobot";
		Integer left=0;
		Integer top=0;
		restTemplate.put(SERVER_URI, null, name,left,top);
		 
		 
	 }
	 
}
