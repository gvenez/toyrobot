package com.anz.toyrobot.models;

import java.io.Serializable;

public class Position implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Position(Integer top, Integer left, Angle angle)
	{
		this.top=top;
		this.left=left;
		this.angle=angle;
	}
	
	
	@Override
	public String toString() {
		return left + "/" + top;
	}


	private Integer top;
	private Integer left;
	private Angle angle;
	public Integer getTop() {
		return top;
	}
	public void setTop(Integer top) {
		this.top = top;
	}
	public Integer getLeft() {
		return left;
	}
	public void setLeft(Integer left) {
		this.left = left;
	}
	public Angle getAngle() {
		return angle;
	}
	public void setAngle(Angle angle) {
		this.angle = angle;
	}
	

}
