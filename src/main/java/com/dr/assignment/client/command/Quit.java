package com.dr.assignment.client.command;

import com.dr.assignment.client.exception.InvalidInputException;

public class Quit extends AbstractCommand {

	@Override
	public void execute() throws InvalidInputException {
		System.exit(0);
	}
}
