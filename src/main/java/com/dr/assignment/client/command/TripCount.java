package com.dr.assignment.client.command;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.dr.assignment.client.dal.RestApiInvokerImpl;
import com.dr.assignment.client.exception.InvalidInputException;
import com.dr.assignment.client.model.TripBooking;
import com.dr.assignment.client.model.TripSearchRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TripCount extends AbstractCommand {

	private RestApiInvokerImpl restInvoker;

	public TripCount() {
		ObjectMapper mapper = new ObjectMapper();
		restInvoker = new RestApiInvokerImpl(mapper);
	}

	@Override
	public void execute() throws InvalidInputException {

		clearConsole();

		TripSearchRequest request = new TripSearchRequest();
		request.setTripBookings(new ArrayList<>());

		try (Scanner scanner = new Scanner(System.in)) {
			String medallion = "";
			String date = "";
			String useCacheStr = "n";
			boolean useCache = false;
			String answer = "y";
			while (answer.equalsIgnoreCase("y")) {
				System.out.print("Enter Cab Medallion: ");
				medallion = scanner.nextLine();
				System.out.print("Enter Trip Date (yyyy-mm-dd): ");
				date = scanner.nextLine();
				System.out.print("Do You Want To Get Data From Cache? (y/n): ");
				useCacheStr = scanner.nextLine();
				if (useCacheStr.equalsIgnoreCase("y")) {
					useCache = true;
				} else {
					useCache = false;
				}
				request.getTripBookings().add(new TripBooking(null, medallion, date, 0));
				System.out.print("Want To Enter More Cab Data (y/n)? ");
				answer = scanner.nextLine();
			}
			try {
				String response = restInvoker.invokeSearch(request, useCache);
				System.out.printf("Number of trips made by cab %s on %s are: \n %s\n", medallion, date, response);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws InvalidInputException {
		TripCount tripCount = new TripCount();

		tripCount.execute();
	}
}
