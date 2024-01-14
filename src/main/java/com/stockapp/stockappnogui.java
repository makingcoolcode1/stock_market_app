
package com.stockapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.Buffer;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class stockappnogui {

    private static String apiUrl = "https://api.twelvedata.com/time_series";
    private static String apiKey = "b31fcc2718da460a83388bca924328c7";

    public static void main(String[] args) {

        
        System.out.println("Enter a ticker to search or type 'quit' at anyime to exit the program: \n");

        Scanner scanner = new Scanner(System.in);

        while (true) {

            String userInput = scanner.nextLine();

            if (userInput.equals("quit")) {
                System.out.println("\n Closing Stock App.....\n");
                break;
            }

            if (userInput.isBlank()) {
                System.out.println("ERROR: Search field cannot be blank... Please enter a ticker to search");
                continue;
                
            }

            try {
                
                URI uri = new URI(buildApiUrl(userInput));
                URL url = uri.toURL();

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    String line;

                    StringBuffer response = new StringBuffer();

                    while ((line = reader.readLine())!=null) {
                        response.append(line);
                        
                    }

                    parseStockData(response.toString());

                    reader.close();
                    
                } else {
                    System.out.println("ERROR: Cannot connect to the API. ERROR CODE: " + connection.getResponseCode());
                }

                connection.disconnect();

            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
            
        }

    }

    public static String buildApiUrl(String userInput) {
        String symbols = userInput;
        String interval = "1min";
        return String.format("%s?symbol=%s&interval=%s&apikey=%s", apiUrl, symbols, interval, apiKey);
        
    }

    private static void parseStockData(String getData) {

        JSONObject json = new JSONObject(getData);

        if (!json.has("values") || json.getJSONArray("values").isEmpty()) {
            System.out.println("ERROR: No Ticker Found");
            return;
            
        }

        JSONArray valueArr = json.getJSONArray("values");
        JSONObject zero = valueArr.getJSONObject(0);

        String openPrice = zero.getString("open");
        String highPRice = zero.getString("high");
        String lowPrice = zero.getString("low");
        String closePRice = zero.getString("close");

        System.out.println("Opening Price: " + openPrice);
        System.out.println("High Price: " + highPRice);
        System.out.println("Low Price: " + lowPrice);
        System.out.println("Closing Price: " + closePRice);


    }

}