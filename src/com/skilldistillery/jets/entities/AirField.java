package com.skilldistillery.jets.entities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AirField {

	private List<Jet> jets;

	public AirField() {
		jets = new ArrayList<>();
		importFromFile();
	}

	public void listFleet() {
		System.out.println("--- Listing all jets in fleet.");
		printFleet(jets);
	}

	public void printFleet(List<Jet> jetList) {
		int index = 1;
		for (Jet jet : jetList) {
			String output = index + ": ";
			output += jet.toString();
			System.out.printf(output);
			System.out.println();
			index++;
		}
	}

	public void flyAllJets() {
		System.out.println("--- Flying all jets in fleet.");
		int index = 1;
		for (Jet jet : jets) {
			String output = index + ": ";
			output += jet.fly();
			System.out.println(output);
			index++;
		}
	}

	public void flyJet(Scanner input) {
		Jet jet = chooseJetFromList(input);
		System.out.println("--- Now flying one jet: ");
		System.out.println(jet.fly());
	}

	public Jet chooseJetFromList(Scanner input) {
		System.out.println("--- Choose a jet.");
		Jet jet = null;
		printFleet(jets);

		int choice = 0;
		do {
			System.out.print("\tYour choice: ");
			choice = input.nextInt();
		} while (choice < 1 || choice > jets.size());

		return jets.get(choice - 1);
	}

	public void viewFastest() {
		Jet fastestJet = jets.get(0);
		for (Jet jet : jets) {
			if (jet.getSpeed() > fastestJet.getSpeed()) {
				fastestJet = jet;
			}
		}
		System.out.println("--- Fastest Jet:\n" + fastestJet.toString());
	}

	public void viewLongest() {
		Jet longestJet = jets.get(0);
		for (Jet jet : jets) {
			if (jet.getRange() > longestJet.getRange()) {
				longestJet = jet;
			}
		}
		System.out.println("--- Jet with Longest Range:\n" + longestJet.toString());
	}

	public void loadCargo() {
		List<CargoPlane> cargoJets = new ArrayList<>();
		int numCargoJets = 0;
		for (Jet jet : jets) {
			if (jet instanceof CargoPlane) {
				cargoJets.add((CargoPlane) jet);
				numCargoJets++;
			}
		}
		if (numCargoJets != 0) {
			System.out.println("--- " + numCargoJets + " Cargo Jets Found: ");
			for (CargoPlane jet : cargoJets) {
				jet.loadCargo();
				System.out.println("cargo loaded into " + jet.getModel());
			}
		} else {
			System.out.println("--- No Cargo Jets in Airfield");
		}
	}
	
	public void dogFight() {
		//get two fighter jets
		List<Jet> fighterPlanes = new ArrayList<>();
		for (Jet jet : jets) {
			if (jet instanceof FighterJet) {
				fighterPlanes.add(jet);
			}
		}
		if (fighterPlanes.size() < 2) {
			System.out.println("--- Dogfight impossible! Only one fighter plane in the fleet.");
		} else {
			int plane1index = (int) Math.floor(Math.random() * fighterPlanes.size());
			int plane2index;
			do {
				plane2index = (int) Math.floor(Math.random() * fighterPlanes.size());
			} while (plane1index == plane2index);
			
			Jet winner;
			Jet loser;
			//50% chance of either plane winning
			int randomNumber = (int) (Math.random() * 10);
			if (randomNumber < 5) {
				winner = fighterPlanes.get(plane1index);
				loser = fighterPlanes.get(plane2index);
			} else {
				loser = fighterPlanes.get(plane1index);
				winner = fighterPlanes.get(plane2index);
			}
			System.out.println("--- DOGFIGHT!!! " + winner.getModel() + " vs " + loser.getModel());
			System.out.println("--- " + winner.getModel() + " destroyed " + loser.getModel());
			System.out.println("--- ... but don't worry, this was only a simulation. ");
			System.out.println("--- ... " + loser.getModel() + " is still in your fleet.");
		}
	}

	public void addJet(Scanner input) {
		// ask for jet info
		System.out.println("--- Add a custom jet.");
		System.out.print("Model: ");
		String model = input.next();
		System.out.print("Speed: ");
		double speed = input.nextDouble();
		System.out.print("Range (int): ");
		int range = input.nextInt();
		System.out.print("Price: ");
		double price = input.nextDouble();
		System.out.println("--- Input received.");

		// ask for jet type
		System.out.println("--- Choose a jet type.");
		System.out.println("1. Normal");
		System.out.println("2. Cargo");
		System.out.println("3. Fighter");
		int choice = 0;
		do {
			System.out.print("\tYour choice: ");
			choice = input.nextInt();
		} while (choice < 1 || choice > 3);

		// determine type of jet and add to ArrayList<Jet> jets
		Jet newJet = null;
		switch (choice) {
		case 1:
			newJet = new JetImpl(model, speed, range, price);
			break;
		case 2:
			newJet = new CargoPlane(model, speed, range, price);
			break;
		case 3:
			newJet = new FighterJet(model, speed, range, price);
			break;
		}
		jets.add(newJet);
		System.out.println("--- Jet added. ");
	}

	public void removeJet(Scanner input) {
		// check if any jets in airfield
		if (jets.size() > 0) {
			int choice = 0;
			Jet toRemove = null;
			System.out.println("--- Remove a jet.");
			System.out.println("1. Enter name");
			System.out.println("2. Choose from list");

			do {
				System.out.print("\tYour choice: ");
				choice = input.nextInt();
			} while (choice < 1 || choice > 2);

			String modelName = "";
			if (choice == 1) {
				System.out.print("\tEnter a model name: ");
				modelName = input.next();

				// anticipate multiple jets with same model name
				List<Jet> foundJets = new ArrayList<>();
				for (Jet jet : jets) {
					if (modelName.toLowerCase().equals(jet.getModel().toLowerCase())) {
						foundJets.add(jet);
					}
				}

				if (foundJets.size() > 1) {
					System.out.println("--- Multiple jets with model name " + modelName + " found.");
					System.out.println("--- Please choose one to remove.");
					printFleet(foundJets);
					System.out.println((foundJets.size() + 1) + ": Remove all " + foundJets.size() + " jets");

					int jetNumber = 0;
					do {
						System.out.print("\tYour choice: ");
						jetNumber = input.nextInt();
					} while (jetNumber < 1 || jetNumber > foundJets.size() + 1);

					// remove one
					if (jetNumber < foundJets.size()) {
						toRemove = foundJets.get(jetNumber - 1);
						jets.remove(toRemove);
						System.out.println("--- Jet removed. ");
					} else {
						// remove all
						for (Jet jet : foundJets) {
							jets.remove(jet);
						}
						System.out.println("--- " + foundJets.size() + " jets removed. ");
					}
				}
			} else {
				System.out.println("--- Choose a jet to remove: ");
				listFleet();
				do {
					System.out.print("\tYour choice: ");
					choice = input.nextInt();
				} while (choice < 1 || choice > jets.size());
				toRemove = jets.get(choice - 1);
				jets.remove(toRemove);
				System.out.println("--- Jet removed. ");
			}
		} else {
			System.out.println("--- Nothing to remove - your AirField is empty!!");
		}
	}

	public void importFromFile() {
		// ArrayList containing strings with delimiter "," from jetdata.txt
		List<String> importList = new ArrayList<>();

		// import data into importList
		try (BufferedReader bufIn = new BufferedReader(new FileReader("jetdata.txt"))) {
			String line;
			while ((line = bufIn.readLine()) != null) {
				importList.add(line);
			}
		} catch (IOException e) {
			System.err.println(e);
		}

		// parse
		for (String line : importList) {
			String[] splits = line.split(",");
			String model = splits[0];
			double speed = Double.valueOf(splits[1]);
			int range = Integer.valueOf(splits[2]);
			double price = Double.valueOf(splits[3]);
			String type = splits[4];
			Jet newJet;
			if (type.toLowerCase().equals("fighter")) {
				jets.add(new FighterJet(model, speed, range, price));
			} else if (type.toLowerCase().equals("cargo")) {
				jets.add(new CargoPlane(model, speed, range, price));
			} else if (type.toLowerCase().equals("normal")) {
				jets.add(new JetImpl(model, speed, range, price));
			} else {
				System.err.println("AirField.importFromFile() ERROR: invalid type");
			}
		}
	}

	public void assignPilots(PilotManager pm) {
		List<String> pilotsAdded = new ArrayList<>();
		String name;

		// avoid pilots being assigned to two planes (otherwise could not fly all)
		for (Jet jet : jets) {
			name = pm.getRandomPilot();
			if (pilotsAdded.size() > 0) {
				for (String pilot : pilotsAdded) {
					while (pilot.toLowerCase().equals(name.toLowerCase())) {
						name = pm.getRandomPilot();
					}
				}
			}

			jet.setPilot(name);
			pilotsAdded.add(name);
		}
	}

	public void save(Scanner input) {
		System.out.println("--- Save to file");
		String fileName;
		
		do {
			System.out.print("Enter a filename (without an extension): ");
			fileName = input.next();
			fileName += ".txt";
		
			if (fileName.equals("jetdata.txt")) {
				System.err.println("ERROR: jetdata.txt is restricted");
			} 
		} while (fileName.equals("jetdata.txt"));
		
		try {
			File myObj = new File(fileName);
			
			// true enables appending data on new lines
			FileWriter myWriter = new FileWriter(fileName, true);
			
			//clear if file already exists
			if (myObj.exists() && myObj.isFile()) {
				//false disables appending data and will clear file
				FileWriter tempWriter = new FileWriter(fileName, false);
				tempWriter.write("");
				tempWriter.close();
			}
			
			//write to file
			int index = 1;
			for (Jet jet : jets) {
				myWriter.write(jet.getInfoToSave());
				if (index != jets.size()) {
					myWriter.write("\n");
					index++;
				}
			}
			myWriter.close();
			
			System.out.println("--- Saved to " + fileName);
			
		} catch (IOException e) {
			System.err.println("AirField.save(): An error occured.");
			e.printStackTrace();
		}
	}
	
}
