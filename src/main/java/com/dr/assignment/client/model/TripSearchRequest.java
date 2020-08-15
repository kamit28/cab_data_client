package com.dr.assignment.client.model;

import java.util.List;

import lombok.Data;


@Data
public class TripSearchRequest {

	private List<TripBooking> tripBookings;
}
