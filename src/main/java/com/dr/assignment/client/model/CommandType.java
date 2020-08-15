package com.dr.assignment.client.model;

import java.util.HashMap;
import java.util.Map;

public enum CommandType {

	TRIP_COUNT("1", 1), ADMIN("2", 2), QUIT("3", 3);

	private static final Map<String, CommandType> lookup = new HashMap<>();

	static {
		for (CommandType t : CommandType.values()) {
			lookup.put(t.commandChar, t);
		}
	}

	private final String commandChar;
	@SuppressWarnings("unused")
	private final int numParams;

	private CommandType(String commandChar, int numParams) {
		this.commandChar = commandChar;
		this.numParams = numParams;
	}

	public boolean equalsCommandChar(String other) {
		return commandChar.equals(other);
	}

	public String toString() {
		return this.commandChar;
	}

	public static CommandType get(String commandChar) {
		return lookup.get(commandChar);
	}
}
