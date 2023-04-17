import java.net.*;
import java.io.*;

public class WebResponseBodyComparator {
    public static void main(String[] args) {
        String url1 = "https://www.example.com/";
        String url2 = "https://www.example.org/";

        try {
            URL obj1 = new URL(url1);
            URL obj2 = new URL(url2);

            HttpURLConnection con1 = (HttpURLConnection) obj1.openConnection();
            HttpURLConnection con2 = (HttpURLConnection) obj2.openConnection();

            // Set request method
            con1.setRequestMethod("GET");
            con2.setRequestMethod("GET");

            int responseCode1 = con1.getResponseCode();
            int responseCode2 = con2.getResponseCode();

            if (responseCode1 != responseCode2) {
                System.out.println("The response codes are different");
                System.out.println("Response code for " + url1 + ": " + responseCode1);
                System.out.println("Response code for " + url2 + ": " + responseCode2);
            } else {
                String responseBody1 = readResponseBody(con1);
                String responseBody2 = readResponseBody(con2);

                if (responseBody1.equals(responseBody2)) {
                    System.out.println("The response bodies are identical");
                } else {
                    System.out.println("The response bodies are different");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String readResponseBody(HttpURLConnection con) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer responseBody = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            responseBody.append(inputLine);
        }

        in.close();

        return responseBody.toString();
    }
}