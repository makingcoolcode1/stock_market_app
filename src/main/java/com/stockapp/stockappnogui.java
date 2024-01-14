
package com.stockapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;

public class stockappnogui {

    private static String apiUrl = "https://api.twelvedata.com/time_series";
    private static String apiKey = "b31fcc2718da460a83388bca924328c7";

    public static void main(String[] args) {
        
        System.out.println("Enter a stock ticker to search or type 'quit' at anytime to exit \n");

        Scanner scanner = new Scanner(System.in);

        while (true) {

            String userInput = scanner.nextLine();
            if (userInput.equals("quit")) {
                System.out.println("\nExiting Stock App \n");
                break;
                
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

                    parseStockData(response.toString(), userInput);

                    reader.close();
                    
                } else {
                    System.out.println("ERROR: Cannot connect to API.... ERROR CODE " + connection.getConnectTimeout());
                }


                connection.disconnect();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                // TODO Auto-generated catch block
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

        JSONArray data = json.getJSONArray("values");


            JSONObject entry = data.getJSONObject(0);

            String openPrice = entry.getString("open");
            String highPrice = entry.getString("high");
            String lowPrice = entry.getString("low");
            String closePrice = entry.getString("close");

            System.out.println("The opening price is: " + openPrice);
            System.out.println("The high price is " + highPrice);
            System.out.println("The low price is " + lowPrice);
            System.out.println("The closing price is " + closePrice);


            

        }

    

    
}