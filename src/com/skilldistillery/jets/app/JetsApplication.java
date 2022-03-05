package com.skilldistillery.jets.app;

import com.skilldistillery.jets.entities.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

public class JetsApplication {

	private AirField airField;
	PilotManager pm;
	Scanner scanner;

	public JetsApplication() {
		pm = new PilotManager();
	}

	public static void main(String[] args) {
		JetsApplication app = new JetsApplication();
		app.launch();
	}

	private void launch() {
		airField = new AirField();
		airField.assignPilots(pm);
		
		scanner = new Scanner(System.in);

		displayUserMenu();

		scanner.close();
	}

	private void displayUserMenu() {
		System.out.println("--- MAIN MENU ---");
		System.out.println("1. List fleet");
		System.out.println("2. Fly all jets");
		System.out.println("3. Fly single jet");
		System.out.println("4. View fastest jet");
		System.out.println("5. View jet with longest range");
		System.out.println("6. Load all Cargo jets");
		System.out.println("7. Dogfight!");
		System.out.println("8. Add a jet to Fleet");
		System.out.println("9. Remove a jet from Fleet");
		System.out.println("10. View pilots");
		System.out.println("11. Hire a new pilot");
		System.out.println("12. Save jets to a file");
		System.out.println("13. Quit");
		doUserMenu(scanner);
	}

	private void doUserMenu(Scanner input) {
		int choice = 0;
		do {
			System.out.print("\tYour choice: ");
			choice = input.nextInt();
		} while (choice < 1 || choice > 13);

		switch (choice) {
		case 1:
			airField.listFleet();
			break;
		case 2:
			airField.flyAllJets();
			break;
		case 3:
			airField.flyJet(scanner);
			break;
		case 4:
			airField.viewFastest();
			break;
		case 5:
			airField.viewLongest();
			break;
		case 6:
			airField.loadCargo();
			break;
		case 7:
			airField.dogFight();
			break;
		case 8:
			airField.addJet(scanner);
			break;
		case 9:
			airField.removeJet(scanner);
			break;
		case 10:
			pm.viewPilots();
			break;
		case 11:
			pm.hirePilot(input);
			break;
		case 12:
			airField.save(scanner);
			break;
		case 13:
			System.out.println("--- Goodbye.");
			break;
		}
		
		if (choice > 0 && choice < 13) {
			displayUserMenu();
		}

	}

}
