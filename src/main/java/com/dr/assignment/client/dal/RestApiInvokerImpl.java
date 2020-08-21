package com.dr.assignment.client.dal;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.Base64;

import com.dr.assignment.client.model.TripSearchRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestApiInvokerImpl {

	private ObjectMapper mapper;

	public RestApiInvokerImpl(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	public String invokeSearch(TripSearchRequest request, boolean useCache) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newBuilder().version(Version.HTTP_2).followRedirects(Redirect.NORMAL).build();

		String requestJson = mapper.writeValueAsString(request);
		System.out.println("Request is: " + requestJson);

		var httpRequest = HttpRequest.newBuilder()
				.uri(URI.create("http://localhost:8080/ny_trips/search?useCache=" + useCache))
				.timeout(Duration.ofSeconds(30)).header("Content-Type", "application/json;")
				.header("Accept", "application/json").POST(BodyPublishers.ofString(requestJson)).build();
		HttpResponse<?> response = client.send(httpRequest, BodyHandlers.ofString());
		System.out.println(response.statusCode());
		if (response.statusCode() == 200) {
			return response.body().toString();
		} else {
			throw new IOException("Not Found");
		}
	}

	public boolean clearCache(String username, String password) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newBuilder().version(Version.HTTP_2).followRedirects(Redirect.NORMAL).build();

		String authHeaderValue = "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());

		var httpRequest = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/ny_trips/admin/flushCache"))
				.timeout(Duration.ofSeconds(30)).header("Authorization", authHeaderValue)
				.header("Accept", "application/json").GET().build();
		HttpResponse<?> response = client.send(httpRequest, BodyHandlers.ofString());
		System.out.println(response.statusCode());
		if (response.statusCode() == 200) {
			return true;
		} else if (response.statusCode() == 401) {
			System.out.println("Authenication failed!!");
			return false;
		} else {
			System.out.println("Action could not be performed successfully!");
			return false;
		}
	}
}
