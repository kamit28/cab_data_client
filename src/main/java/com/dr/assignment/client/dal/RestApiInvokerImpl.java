package com.dr.assignment.client.dal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import com.dr.assignment.client.model.TripSearchRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestApiInvokerImpl {

	private ObjectMapper mapper;

	public RestApiInvokerImpl(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	public String invokeSearch(TripSearchRequest request, boolean useCache) throws MalformedURLException, IOException {
		HttpURLConnection con = (HttpURLConnection) new URL(
				"http://localhost:8080/ny_trips/search?useCache=" + useCache).openConnection();

		String requestJson = mapper.writeValueAsString(request);
		System.out.println("Request is: " + requestJson);
		con.setRequestMethod("POST");

		con.setDoOutput(true);
		con.setDoInput(true);

		con.setRequestProperty("Content-Type", "application/json;");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Method", "POST");
		OutputStream os = con.getOutputStream();
		os.write(requestJson.toString().getBytes("UTF-8"));
		os.close();

		StringBuilder sb = new StringBuilder();
		int HttpResult = con.getResponseCode();
		if (HttpResult == HttpURLConnection.HTTP_OK) {
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));

			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			br.close();
			return sb.toString();
		} else {
			System.out.println(con.getResponseCode());
			System.out.println(con.getResponseMessage());
			throw new IOException("Not Found");
		}
	}

	public boolean clearCache(String username, String password) throws MalformedURLException, IOException {
		HttpURLConnection con = (HttpURLConnection) new URL("http://localhost:8080/ny_trips/admin/flushCache")
				.openConnection();
		String cred = username + ":" + password;
		byte[] encodeCred = Base64.getEncoder().encode(cred.getBytes(StandardCharsets.UTF_8));
		String authHeaderValue = "Basic " + new String(encodeCred);

		con.setRequestMethod("GET");

		con.setDoOutput(true);
		con.setDoInput(true);

		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Method", "GET");
		con.setRequestProperty("Authorization", authHeaderValue);
		int responseCode = con.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			System.out.println(response.toString());
			return true;
		} else if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
			System.out.println("Authenication failed!!");
			return false;
		} else {
			System.out.println("Action could not be performed successfully!");
			return false;
		}
	}
}
