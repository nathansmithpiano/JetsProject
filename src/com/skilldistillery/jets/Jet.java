package com.skilldistillery.jets;

public abstract class Jet {
	
	private String model;
	private double speed;
	private int range;
	private long price;

	public Jet(String model, double speed, int range, long price) {
		this.model = model;
		this.speed = speed;
		this.range = range;
		this.price = price;
	}
	
	public void fly() {
		
	}
	
	public double getSpeedInMach() {
		double value = 0.0;
		
		return value;
	}

}
