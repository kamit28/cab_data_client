package com.dr.assignment.client.command;

import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import com.dr.assignment.client.dal.RestApiInvokerImpl;
import com.dr.assignment.client.exception.InvalidInputException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AdminMenu extends AbstractCommand {

	private RestApiInvokerImpl restInvoker;

	public AdminMenu() {
		ObjectMapper mapper = new ObjectMapper();
		restInvoker = new RestApiInvokerImpl(mapper);
	}

	@Override
	public void execute() throws InvalidInputException {
		clearConsole();

		try (Scanner scanner = new Scanner(System.in)) {
			String answer = "y";
			while (true) {
				System.out.println("Available actions: ");
				System.out.println("1. Clear Cache");
				System.out.println("2. Exit");
				System.out.print("Your Answer? ");
				answer = scanner.nextLine();
				switch (answer) {
				case "1":
					System.out.print("Please enter admin username: ");
					String username = scanner.nextLine();
					System.out.print("Please enter admin password: ");
					String password = scanner.nextLine();
					performClearCache(username, password);
					break;
				case "2":
					return;
				default:
					System.out.println("Invalid option! Please try again");
				}
			}
		}
	}

	private boolean performClearCache(String username, String password) {
		if (StringUtils.isAnyEmpty(username, password)) {
			System.out.println("Either username or password are empty!");
			return false;
		} else {
			try {
				return restInvoker.clearCache(username, password);
			} catch (IOException e) {
				System.out.println(e.getLocalizedMessage());
				return false;
			}
		}

	}

}
