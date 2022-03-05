package com.skilldistillery.jets.entities;

import java.text.DecimalFormat;

public abstract class Jet {
	
	private String model;
	private double speed;
	private int range;
	private double price;

	public Jet(String model, double speed, int range, double price) {
		this.model = model;
		this.speed = speed;
		this.range = range;
		this.price = price;
	}
	
	public String fly() {
		
		return toString() + " | Duration: " + getDuration();
		
	}
	
	public String getDuration() {
		//returns output as Hours:Minutes:Seconds
		String output = "";
		double time = range / speed;
		
		int value;
		for (int i = 0; i < 3; i++) {
			value = (int) Math.floor(time);
			//leading 0
			if (value < 10) {
				output += "0";
			}
			output += value;
			// : not at end
			if (i != 2) {
				output += ":";
				time = 60 * (time % value);
			}
		}
		return output;
	}
	
	public String getModel() {
		return model;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public int getRange() {
		return range;
	}
	
	public double getSpeedInMach() {
		double value = 0.0;
		
		return value;
	}
	
	@Override
	public String toString() {
		DecimalFormat priceFormat = new DecimalFormat("#,###,###.00");
		DecimalFormat speedFormat = new DecimalFormat("#,###,###.##");
		String output = "";
		output += "Model: " + model;
		output += " | Speed: " + speedFormat.format(speed) + " mph";
		output += " | Range: " + range + " mi";
		output += " | Price: $" + priceFormat.format(price);
		return output;
	}

}
