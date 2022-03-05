package com.skilldistillery.jets.app;

import com.skilldistillery.jets.entities.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

public class JetsApplication {
	
	private AirField airField;
	Scanner scanner;
	

	public JetsApplication() {
		// TODO Auto-generated constructor stub
		
	}
	
	public static void main(String[] args) {
		JetsApplication app = new JetsApplication();
		app.launch();
	}
	
	private void launch() {
		airField = new AirField();
		scanner = new Scanner(System.in);
		
		airField.loadCargo();
//		displayUserMenu();
//		doUserMenu(scanner);
		
		scanner.close();
	}
	
	private void displayUserMenu() {
		System.out.println("--- MAIN MENU ---");
		System.out.println("1. List fleet");
		System.out.println("2. Fly all jets");
		System.out.println("3. View fastest jet");
		System.out.println("4. View jet with longest range");
		System.out.println("5. Load all Cargo jets");
		System.out.println("6. Dogfight!");
		System.out.println("7. Add a jet to Fleet");
		System.out.println("8. Remove a jet from Fleet");
		System.out.println("9. Quit");
	}
	
	private void doUserMenu(Scanner input) {
		int choice = 0;
		do {
			System.out.print("\tYour choice: ");
			choice = input.nextInt();
		} while (choice < 1 || choice > 9);
		
		switch (choice) {
		case 1:
			airField.listFleet();
			break;
		case 2:
			airField.flyAllJets();
			break;
		case 3:
			airField.viewFastest();
			break;
		case 4:
			airField.viewLongest();
			break;
		case 5:
			airField.loadCargo();
			break;
		case 6:
		case 7:
		case 8:
		case 9:
			System.out.println("Goodbye.");
			break;
		}
		
	}

}
