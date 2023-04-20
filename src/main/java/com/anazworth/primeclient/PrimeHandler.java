package com.anazworth.primeclient;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

public class PrimeHandler {

    public static void getPrime(String input) throws IOException {
        URL url = new URL("http://localhost:8080/primes/" + input);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        int status = con.getResponseCode();
        if (status == 200) {
            DataInputStream inputStream = new DataInputStream(con.getInputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            if (content.toString().equals("true")) {
                PrimeClientApplication.log(input + " is a prime number.");
            } else {
                PrimeClientApplication.log(input + " is not a prime number.");
            }
        } else {
            PrimeClientApplication.log("Error: " + status);
        }
    }

    public static boolean request(DataInputStream inputStream, DataOutputStream outputStream, String input) {
        try (Socket socket = new Socket("localhost", 8080)) {
            outputStream.writeInt(Integer.parseInt(input));
            outputStream.flush();
            return inputStream.readBoolean();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
