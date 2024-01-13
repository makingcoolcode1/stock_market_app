
package com.stockapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class stockappnogui {

    private static String apiUrl = "https://api.twelvedata.com/time_series";
    private static String apiKey = "b31fcc2718da460a83388bca924328c7";

    public static void main(String[] args) {
        
        System.out.println("Enter a ticker to searhc or type 'quit' to exit");
        Scanner scanner = new Scanner(System.in);


        while (true) {

            String useRInput = scanner.nextLine();
            if (useRInput.equals("quit")) {
                System.out.println("Exitng Application");
                break;
                
            }

            try {

                URI uri = new URI(buildApiUrl(useRInput));
                URL url = uri.toURL();


                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    String line;

                    StringBuffer response = new StringBuffer();

                    while ((line = reader.readLine())!= null) {
                        response.append(line);
                        
                    }

                    parseStockData(response.toString(), useRInput);
                    
                } else {
                    System.out.println("ERROR: Cannot connect to API. ERROR CODE: ");
                }
    
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    }

    public static String buildApiUrl(String userInput) {

        String symbols = userInput;
        String interval = "1min";

        return String.format("%s?symbol=%s&interval=%s&apikey=%s", apiUrl, symbols, interval, apiKey);
    }

    private static void parseStockData(String getData, String symbol) {


        JSONObject json = new JSONObject(getData);
        JSONArray data = json.getJSONArray("values")
;
        for (int i = 0; i < data.length(); i++) {
            JSONObject entry = data.getJSONObject(i);


        String openPrice = entry.getString("open");
        String highPrice = entry.getString("high");
        String lowPrice = entry.getString("low");
        String closePrice = entry.getString("close");

        

        System.out.println("\n" + openPrice);
        System.out.println("\n" + highPrice);
        System.out.println("\n" + lowPrice);
        System.out.println("\n" + closePrice);
        }
        



        

    }

}