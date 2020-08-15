package com.dr.assignment.client.command;

import com.dr.assignment.client.exception.InvalidInputException;

public interface Command {
	public void execute() throws InvalidInputException;
}
