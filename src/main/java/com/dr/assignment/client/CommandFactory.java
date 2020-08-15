package com.dr.assignment.client;

import com.dr.assignment.client.command.AbstractCommand;
import com.dr.assignment.client.command.AdminMenu;
import com.dr.assignment.client.command.Quit;
import com.dr.assignment.client.command.TripCount;
import com.dr.assignment.client.exception.InvalidInputException;
import com.dr.assignment.client.model.CommandType;

public class CommandFactory {

	public final AbstractCommand getCommand(String input) throws InvalidInputException {
		switch (CommandType.get(input)) {
		case TRIP_COUNT:
			return new TripCount();
		case ADMIN:
			return new AdminMenu();
		case QUIT:
			return new Quit();
		default:
			throw new InvalidInputException("Invalid command!");
		}
	}

}
