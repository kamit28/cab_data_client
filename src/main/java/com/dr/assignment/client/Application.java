package com.dr.assignment.client;

import java.util.Scanner;

import com.dr.assignment.client.command.Command;
import com.dr.assignment.client.exception.InvalidInputException;

public class Application {

	private final CommandFactory factory = new CommandFactory();

	public static void main(String[] args) {
		System.out.println("======================================");
		System.out.println("\tNY Cab Data Lookup CLI");
		System.out.println("======================================");
		Application app = new Application();
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("\nPlease Eneter your choice:");
			System.out.println("1. Get Trip Count For A Cab");
			System.out.println("2. Enter Admin Mode");
			System.out.println("3. Exit");
			System.out.print("Your Answer? ");
			try {
				String answer = scanner.next();
				app.executeCommnad(answer);
				System.out.println("Bye!!");
				scanner.reset();
			} catch (InvalidInputException iie) {
				System.out.println(iie.getMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void executeCommnad(String input) throws InvalidInputException {
		Command command = factory.getCommand(input);
		command.execute();
	}
}
