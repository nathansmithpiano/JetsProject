package com.skilldistillery.jets.entities;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class PilotManager {
	
	private static List<String> pilotNames = new ArrayList<>();

	public PilotManager() {
		populateNames();
	}
	
	public void hirePilot(Scanner input) {
		boolean duplicateFound = true;
		String name = "Adam";
		
		System.out.println("--- Hire a new pilot.");
		
		do {
			System.out.print("\tEnter a name: ");
			name = input.next();
			
			//check for duplicates
			for (String pilot : pilotNames) {
				if (name.toLowerCase().equals(pilot.toLowerCase())) {
					duplicateFound = true;
					break;
				} else {
					duplicateFound = false;
				}
			}
			if (duplicateFound) {
				System.out.println("Duplicate name found. Name must be unique.");
			}
		} while (duplicateFound);
		
		pilotNames.add(name);
		System.out.println("--- New pilot " + name + " has been hired");
	}
	
	public void viewPilots() {
		System.out.println("--- Viewing all pilots:");
		
		int index = 1;
		for (String name : pilotNames) {
			System.out.println(index + ": " + name);
			index++;
		}
	}
	
	private void populateNames() {
		pilotNames.add("Adam");
		pilotNames.add("Bob");
		pilotNames.add("Charlie");
		pilotNames.add("Doug");
		pilotNames.add("Evan");
		pilotNames.add("Frank");
		pilotNames.add("George");
		pilotNames.add("Henry");
		pilotNames.add("Ivan");
		pilotNames.add("Jacob");
		pilotNames.add("Kevin");
		pilotNames.add("Larry");
		pilotNames.add("Mike");
		pilotNames.add("Nathan");
		pilotNames.add("Oscar");
		pilotNames.add("Peter");
		pilotNames.add("Quentin");
		pilotNames.add("Ralph");
		pilotNames.add("Sam");
		pilotNames.add("Thomas");
		pilotNames.add("Ulysses");
		pilotNames.add("Victor");
		pilotNames.add("Wyatt");
		pilotNames.add("Xavier");
		pilotNames.add("Yamir");
		pilotNames.add("Zach");
	}
	
	public String getRandomPilot() {
		int pilotNum = (int) Math.floor(Math.random() * pilotNames.size());
		return pilotNames.get(pilotNum);
	}
}
