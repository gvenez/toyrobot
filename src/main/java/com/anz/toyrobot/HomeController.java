package com.anz.toyrobot;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anz.toyrobot.models.Angle;
import com.anz.toyrobot.models.Position;
import com.anz.toyrobot.models.Robot;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	Map<String, Robot> allrobots = new HashMap<String, Robot>();
	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	
	@RequestMapping(value = "/{name}/{left}/{top}", method = RequestMethod.PUT)
	public @ResponseBody Robot putRobot(@PathVariable("name") String name, @PathVariable("left") int left,@PathVariable("top") int top ) {
		
		Robot robot;
		robot = allrobots.get(name);
		if (null == robot) {
			logger.info("Creating new Robot: " + name);
			robot = new Robot();
			robot.setErrorDescription("New Robot created");
		}
		else
		{
			robot.setErrorDescription("Robot exists");
		}
		robot.setName(name);
		 Position p = new Position(top,left, Angle.NORTH);
		 robot.setPosition(p);
		 
		allrobots.put(robot.getName(), robot);	
		return robot;
	}
	
	@RequestMapping(value = "/robots.html", method = RequestMethod.GET)
	public String gethome() {	
		return "home";
	}
	
	
	
	/**
	 * Simply selects the robot to render by returning its name.
	 */
	@RequestMapping(value = "/{name}/position/{command}/{amount}", method = RequestMethod.PUT)
	public @ResponseBody Robot getStatus( @PathVariable("name") String name,  @PathVariable("command") String cmd,  @PathVariable("amount") int amount) {

		Date date = new Date();
		logger.info("Robot controller: moving: " + cmd + " by "+ amount);

		Robot robot;
		robot = allrobots.get(name);
		if (null == robot) {
			logger.info("Creating new Robot: " + name);
			robot = new Robot();
			robot.setName(name);
		}
		
		Position position = robot.getPosition();
		
		
		
		if(null==position)
		{
			position = new Position(0,0,Angle.NORTH);
		}
		
		//TODO ideally clone this object
		Position tempposition= new Position(position.getTop(),position.getLeft(),Angle.NORTH); 
		logger.info( "Current position to " + tempposition);
		switch(cmd)
		{
		case "left":
			tempposition.setLeft(position.getLeft()+amount);
			break;
		case "top":
			tempposition.setTop(position.getTop()+amount);
			break;
			
		}
		logger.info( "setting new position to " + tempposition);
		if(robot.checkValid(tempposition))
		{
			robot.setPosition(tempposition);
			robot.setErrorDescription("Robot moved to " + cmd + " for " + amount);
		}
		else
		{
			robot.setErrorDescription("Invalid position!" + cmd );
		}
		
		

		allrobots.put(name, robot);
		
		return robot;
	}
	
	
	
	@RequestMapping(value = "/{name}/position", method = RequestMethod.GET)
	public @ResponseBody Position getPosition(@PathVariable("name") String name) {

		Robot robot;
		robot = allrobots.get(name);
		if (null == robot) {
			logger.info("Creating new Robot: " + name);
			robot = new Robot();
		}
			
		allrobots.put(name, robot);
		
		return robot.getPosition();
	}
	

	@RequestMapping(value = "/{name}/get", method = RequestMethod.GET)
	public @ResponseBody Robot getRobot(@PathVariable("name") String name) {

		Robot robot;
		robot = allrobots.get(name);
		if (null == robot) {
			logger.info("Creating new Robot: " + name);
			robot = new Robot();
		}
			
		 Position p = new Position(10,10, Angle.NORTH);
		 robot.setPosition(p);
		 
		allrobots.put(name, robot);
		
		return robot;
	}
	
}
