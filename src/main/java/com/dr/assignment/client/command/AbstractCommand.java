package com.dr.assignment.client.command;

import com.dr.assignment.client.exception.InvalidInputException;

public abstract class AbstractCommand implements Command {

	private static final String OS = System.getProperty("os.name");

	@Override
	public abstract void execute() throws InvalidInputException;

	protected final void clearConsole() {
		try {
			if (OS.contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				System.out.print("\033\143");
			}
		} catch (final Exception e) {
			System.err.println(e.getMessage());
			System.exit(0);
		}
	}
}
