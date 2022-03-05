package com.skilldistillery.jets.entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AirField {
	
	private List<Jet> jets;

	public AirField() {
		jets = new ArrayList<>();
		importFromFile();
	}
	
	public void listFleet() {
		int index = 1;
		for (Jet jet : jets) {
			String output = index + ": ";
			output += jet.toString();
			System.out.printf(output);
			//newline if not last
			if (index != jets.size()) {
				System.out.println();
			}
			index++;
		}
	}
	
	public void flyAllJets() {
		int index = 1;
		for (Jet jet : jets) {
			String output = index + ": ";
			output += jet.fly();
			System.out.println(output);
			index++;
		}
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
	
	public void importFromFile() {
		//ArrayList containing strings with delimiter "," from jetdata.txt
		List<String> importList = new ArrayList<>();
		
		//import data into importList
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
}
