package com.anz.toyrobot.models;

import java.io.Serializable;

public class Robot implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String description;
	private Position position;
	private int cmdStatus=0;
	private String errorDescription;
	
	
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public int getCmdStatus() {
		return cmdStatus;
	}
	public void setCmdStatus(int cmdStatus) {
		this.cmdStatus = cmdStatus;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	public boolean checkValid(Position position)
	{
		if(position.getLeft()<0) return false;
		if(position.getTop()<0) return false;
		
		return true;
	}
	
}
